package it.unipd.dei.eis.presentation;

import it.unipd.dei.eis.core.common.Either;
import it.unipd.dei.eis.core.common.Failure;
import it.unipd.dei.eis.core.common.Success;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the AsyncExecutor class.
 */
class AsyncExecutorTest {

    /**
     * Test the execute method.
     */
    @Test
    void execute() {
        AsyncExecutor asyncExecutor = AsyncExecutor.getInstance();
        asyncExecutor.execute(() -> getEither(true), "test");
        assertThrows(RuntimeException.class, () -> asyncExecutor.execute(() -> getEither(false), "test"));
    }

    /**
     * Utility method to get an Either instance and simulate a long operation.
     */
    private Either<Failure, Success> getEither(boolean success) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {
        }
        if (success) {
            return Either.success(new Success());
        } else {
            return Either.failure(new Failure(new Exception()));
        }
    }
}