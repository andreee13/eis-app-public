package it.unipd.dei.eis.presentation;

import it.unipd.dei.eis.core.common.Either;
import it.unipd.dei.eis.core.common.Failure;
import it.unipd.dei.eis.core.common.Success;

import java.io.PrintStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * The AsyncExecutor class is the singleton class that executes the controllers.
 * It contains the execute method.
 */
public class AsyncExecutor {

    /**
     * The ANIMATION field is the animation of the loading thread.
     */
    private static final String[] ANIMATION = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};

    /**
     * The ANIMATION_INTERVAL field is the interval between two frames of the animation.
     */
    private static final int ANIMATION_INTERVAL = 100;

    /**
     * The ANSI_GREEN field is the ANSI code for green.
     */
    private static final String ANSI_GREEN = "\u001B[32m";

    /**
     * The ANSI_RESET field is the ANSI code for reset.
     */
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * The instance field is the instance of the AsyncExecutor class.
     */
    private static AsyncExecutor instance;

    /**
     * The AsyncExecutor constructor.
     * It sets the animation of the loading thread.
     */
    private AsyncExecutor() {
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
     * The getInstance method returns the instance of the AsyncExecutor class.
     *
     * @return the instance of the AsyncExecutor class
     */
    public static synchronized AsyncExecutor getInstance() {
        if (instance == null) {
            instance = new AsyncExecutor();
        }
        return instance;
    }

    /**
     * The abort method aborts the loading thread.
     * Find the cause of the exception and throw a RuntimeException.
     *
     * @param loadingThread the loading thread
     * @param e             the exception
     */
    @SuppressWarnings("StatementWithEmptyBody")
    private void abort(Thread loadingThread, Exception e) {
        Throwable throwable;
        for (throwable = e; throwable.getCause() != null; throwable = throwable.getCause()) ;
        loadingThread.interrupt();
        try {
            Thread.sleep(ANIMATION_INTERVAL);
        } catch (InterruptedException ignored) {
        }
        throw new RuntimeException("\r[X] Error: " + (throwable.getMessage() == null ? "Unknown error" : throwable.getMessage()));
    }

    /**
     * The execute method executes the controller.
     *
     * @param supplier the function to be executed
     */
    public void execute(Supplier<Either<Failure, Success>> supplier, String name) {
        System.out.printf("%s:\n", name);
        long start = System.currentTimeMillis();
        Thread loadingThread = getLoadingThread(start);
        loadingThread.start();
        try {
            Either<Failure, Success> result = CompletableFuture.supplyAsync(supplier)
                    .get();
            if (result.isFailure()) {
                abort(loadingThread, result.failure.exception);
            }
            loadingThread.interrupt();
            System.out.printf(ANSI_GREEN + "\r[✓] Success • %s\n\n", getLoadingTime(start) + ANSI_RESET);
        } catch (InterruptedException | ExecutionException e) {
            abort(loadingThread, e);
        }
    }

    /**
     * The getLoadingThread method returns the loading thread.
     *
     * @return the loading thread
     */
    @SuppressWarnings("BusyWait")
    private Thread getLoadingThread(long start) {
        return new Thread(() -> {
            int animIndex = 0;
            while (!Thread.currentThread()
                    .isInterrupted()) {
                System.out.printf("\r[%s] Loading • %s", ANIMATION[animIndex], getLoadingTime(start));
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

    /**
     * The getLoadingTime method returns the loading time as string.
     *
     * @param start the start time
     * @return the loading time
     */
    private String getLoadingTime(long start) {
        long time = System.currentTimeMillis() - start;
        if (time < 1000) {
            return String.format("%d ms", time);
        }
        return String.format("%d.%01ds", time / 1000, (time % 1000) / 100);
    }
}