package it.unipd.dei.eis.core.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the IntegerParser class.
 */
class IntegerParserTest {

    /**
     * Test the tryParse method.
     */
    @Test
    void tryParse() {
        Integer i = IntegerParser.tryParse("10");
        assertNotNull(i);
        assertEquals(10, i.intValue());
    }
}