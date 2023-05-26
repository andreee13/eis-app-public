package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.presentation.Context;

import java.io.PrintStream;
import java.util.concurrent.CompletableFuture;

public class ControllerExecutor {
    private static final String[] ANIMATION = {"⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏"};
    private static final int ANIMATION_INTERVAL = 150;
    private static ControllerExecutor instance;

    private ControllerExecutor() {
        System.setOut(new PrintStream(System.out) {
            @Override
            public void println(String x) {
                super.println("\r" + " - " + x);
            }
        });
    }

    public static ControllerExecutor getInstance() {
        if (instance == null) {
            instance = new ControllerExecutor();
        }
        return instance;
    }

    private void abort(Thread loadingThread, Either<Failure, Success> result) {
        abort(loadingThread, result.failure.message + "\n\t-> Caused by " + result.failure.exception.getMessage());
    }

    private void abort(Thread loadingThread, Exception e) {
        abort(loadingThread, e.getMessage());
    }

    private void abort(Thread loadingThread, String s) {
        loadingThread.interrupt();
        System.out.println("\r[X] Error: " + s);
        System.exit(1);
    }

    public void execute(Controller controller, Context context) {
        System.out.print(controller.name.toUpperCase() + ":\n");
        CompletableFuture<Either<Failure, Success>> completableFuture = CompletableFuture.supplyAsync(() -> controller.execute(context));
        Thread loadingThread = getLoadingThread();
        loadingThread.start();
        try {
            Either<Failure, Success> result = completableFuture.get();
            if (result.isFailure()) {
                abort(loadingThread, result);
            }
            loadingThread.interrupt();
            System.out.println("\r[✓] Success");
        } catch (Exception e) {
            abort(loadingThread, e);
        }
        System.out.println();
    }

    @SuppressWarnings("all")
    private Thread getLoadingThread() {
        return new Thread(() -> {
            int animIndex = 0;
            while (!Thread.currentThread()
                    .isInterrupted()) {
                System.out.print("\r[" + ANIMATION[animIndex] + "] Loading");
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
