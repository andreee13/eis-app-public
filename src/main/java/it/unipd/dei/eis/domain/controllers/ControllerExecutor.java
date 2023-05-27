package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.presentation.Context;

import java.io.PrintStream;
import java.util.concurrent.CompletableFuture;

/**
 * The ControllerExecutor class is the singleton class that executes the controllers.
 * It contains the execute method.
 */
public class ControllerExecutor {

    /**
     * The ANIMATION field is the animation of the loading thread.
     */
    private static final String[] ANIMATION = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};

    /**
     * The ANIMATION_INTERVAL field is the interval between two frames of the animation.
     */
    private static final int ANIMATION_INTERVAL = 150;

    /**
     * The instance field is the instance of the ControllerExecutor class.
     */
    private static ControllerExecutor instance;

    /**
     * The ControllerExecutor constructor.
     * It sets the animation of the loading thread.
     */
    private ControllerExecutor() {
        System.setOut(new PrintStream(System.out) {

            /**
             * The println method prints the operation of the controller.
             */
            @Override
            public void println(String x) {
                super.printf("\r - %s%n", x);
            }
        });
    }

    /**
     * The getInstance method returns the instance of the ControllerExecutor class.
     *
     * @return the instance of the ControllerExecutor class
     */
    public static synchronized ControllerExecutor getInstance() {
        if (instance == null) {
            instance = new ControllerExecutor();
        }
        return instance;
    }

    /**
     * The abort method aborts the loading thread.
     *
     * @param loadingThread the loading thread
     * @param result        the result of the controller
     */
    private void abort(Thread loadingThread, Either<Failure, Success> result) {
        abort(loadingThread, String.format("%s\n\t-> Caused by %s", result.failure.message, result.failure.exception.getMessage()));
    }

    /**
     * The abort method aborts the loading thread.
     *
     * @param loadingThread the loading thread
     * @param e             the exception
     */
    private void abort(Thread loadingThread, Exception e) {
        abort(loadingThread, e.getMessage());
    }

    /**
     * The abort method aborts the loading thread.
     *
     * @param loadingThread the loading thread
     * @param s             the string of the error
     */
    private void abort(Thread loadingThread, String s) {
        loadingThread.interrupt();
        System.out.println("\r[X] Error: " + s);
        System.exit(1);
    }

    /**
     * The execute method executes the controller.
     *
     * @param controller the controller
     * @param context    the context of the controller
     */
    public void execute(Controller controller, Context context) {
        System.out.printf("%s:\n", controller.name.toUpperCase());
        CompletableFuture<Either<Failure, Success>> completableFuture = CompletableFuture.supplyAsync(() -> controller.execute(context));
        Thread loadingThread = getLoadingThread();
        loadingThread.start();
        try {
            Either<Failure, Success> result = completableFuture.get();
            if (result.isFailure()) {
                abort(loadingThread, result);
            }
            loadingThread.interrupt();
            System.out.println("\r[✓] Success\n");
        } catch (Exception e) {
            abort(loadingThread, e);
        }
    }

    /**
     * The getLoadingThread method returns the loading thread.
     *
     * @return the loading thread
     */
    @SuppressWarnings("BusyWait")
    private Thread getLoadingThread() {
        return new Thread(() -> {
            int animIndex = 0;
            while (!Thread.currentThread()
                    .isInterrupted()) {
                System.out.printf("\r[%s] Loading", ANIMATION[animIndex]);
                animIndex = (animIndex + 1) % ANIMATION.length;
                try {
                    Thread.sleep(ANIMATION_INTERVAL);
                } catch (InterruptedException e) {
                    Thread.currentThread()
                            .interrupt();
                }
            }
        });
    }

}
