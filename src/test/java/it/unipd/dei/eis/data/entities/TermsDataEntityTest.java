package it.unipd.dei.eis.data.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the TermsDataEntity class.
 */
public class TermsDataEntityTest {

    /**
     * Test the toString method.
     */
    @Test
    void testToString(){
        TermsDataEntity t = new TermsDataEntity("title", "body");
        String s = t.toString();
        assertEquals(s, "title body");
    }

    /**
     * Test the contains method.
     */
    @Test
    void contains(){
        TermsDataEntity t = new TermsDataEntity("title", "body");
        assertTrue(t.contains("title"));
    }
}
