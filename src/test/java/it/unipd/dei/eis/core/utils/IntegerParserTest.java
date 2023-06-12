package it.unipd.dei.eis.core.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the IntegerParser class.
 */
class IntegerParserTest {

    /**
     * Test the tryParse method.
     */
    @Test
    void tryParse() {
        Integer i1 = IntegerParser.tryParse("10");
        assertNotNull(i1);
        assertEquals(10, i1.intValue());
        Integer i2 = IntegerParser.tryParse("1A");
        assertNull(i2);
    }
}