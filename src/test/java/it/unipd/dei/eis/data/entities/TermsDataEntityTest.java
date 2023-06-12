package it.unipd.dei.eis.data.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the TermsDataEntity class.
 */
public class TermsDataEntityTest {

    /**
     * Test the toString method.
     */
    @Test
    void testToString() {
        TermsDataEntity t = new TermsDataEntity("title", "body");
        String s = t.toString();
        assertEquals(s, "title body");
    }

    /**
     * Test the contains method.
     */
    @Test
    void contains() {
        TermsDataEntity entity = new TermsDataEntity("title", "body");
        assertTrue(entity.contains("title"));
        assertTrue(entity.contains("body"));
        assertFalse(entity.contains("not present"));
    }
}
