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
    @SuppressWarnings({"AccessStaticViaInstance", "InstantiationOfUtilityClass"})
    void mainTest() {
        String[] args = {"src/test/resources/file.csv", "-d", "-ca", "1"};
        new App().main(args);
    }
}