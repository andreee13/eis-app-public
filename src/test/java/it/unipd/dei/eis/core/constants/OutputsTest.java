package it.unipd.dei.eis.core.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the Outputs class.
 */
class OutputsTest {

    /**
     * Test the isArticlesOutput method.
     */
    @Test
    void isArticlesOutput() {
        boolean b1 = Outputs.isArticlesOutput("articles.json");
        assertTrue(b1);
        boolean b2 = Outputs.isArticlesOutput("articles.xml");
        assertFalse(b2);
    }

    /**
     * Test the isTermsOutput method.
     */
    @Test
    void isTermsOutput() {
        boolean b1 = Outputs.isTermsOutput("terms.txt");
        assertTrue(b1);
        boolean b2 = Outputs.isTermsOutput("terms.xml");
        assertFalse(b2);
    }
}