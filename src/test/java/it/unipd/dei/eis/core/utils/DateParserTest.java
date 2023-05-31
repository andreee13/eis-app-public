package it.unipd.dei.eis.core.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the DateParser class.
 */
class DateParserTest {

    /**
     * Test the tryParse method.
     */
    @Test
    void tryParse() {
        Date date = DateParser.tryParse("2023-01-01");
        assertNotNull(date);
        assertEquals(1672527600000L, date.getTime());
    }

    /**
     * Test the tryFormat method.
     */
    @Test
    void tryFormat() {
        String s = DateParser.tryFormat(new Date());
        assertNotNull(s);
    }
}