package it.unipd.dei.eis.core.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test the Failure class.
 */
class FailureTest {

    /**
     * Test the constructor.
     */
    @Test
    void testConstructor() {
        Failure f1 = new Failure("message");
        assertEquals("message", f1.message);
        Failure f2 = new Failure(new Exception("exception"), "message");
        assertEquals("message", f2.message);
        assertNotNull(f2.exception);
        assertEquals("exception", f2.exception.getMessage());
    }
}