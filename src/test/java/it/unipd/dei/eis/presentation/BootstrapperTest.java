package it.unipd.dei.eis.presentation;

import org.junit.jupiter.api.Test;

/**
 * Test the Bootstrapper class.
 */
class BootstrapperTest {

    /**
     * Test the launch method.
     */
    @Test
    void launch() {
        String[] args = new String[]{"-j"};
        new Bootstrapper(args).launch();
    }
}