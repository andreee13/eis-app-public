package it.unipd.dei.eis;

import org.junit.jupiter.api.Test;

/**
 * Test class for the App class.
 */
class AppTest {

    /**
     * Test the main method.
     */
    @Test
    void mainTest() {
        String[] args = {"src/test/resources/file.csv", "-d", "-ca", "1"};
        App.main(args);
    }
}