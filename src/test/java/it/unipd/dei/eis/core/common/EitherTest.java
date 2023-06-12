package it.unipd.dei.eis.core.common;

import it.unipd.dei.eis.core.common.Either;
import it.unipd.dei.eis.core.common.Failure;
import it.unipd.dei.eis.core.common.Success;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the Either class.
 */
class EitherTest {

    /**
     * Test the isFailure method.
     */
    @Test
    void isFailure() {
        Either<Failure, Success> either = Either.failure(new Failure(new Exception()));
        assertTrue(either.isFailure());
    }

    /**
     * Test the isSuccess method.
     */
    @Test
    void isSuccess() {
        Either<Failure, Success> either = Either.success(new Success());
        assertTrue(either.isSuccess());
    }
}