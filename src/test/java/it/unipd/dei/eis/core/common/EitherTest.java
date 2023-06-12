package it.unipd.dei.eis.core.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertFalse(either.isSuccess());
    }

    /**
     * Test the isSuccess method.
     */
    @Test
    void isSuccess() {
        Either<Failure, Success> either = Either.success(new Success());
        assertTrue(either.isSuccess());
        assertFalse(either.isFailure());
    }
}