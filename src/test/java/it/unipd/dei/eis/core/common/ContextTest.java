package it.unipd.dei.eis.core.common;

import it.unipd.dei.eis.presentation.Bootstrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the Context class.
 */
class ContextTest {

    /**
     * Test the constructor.
     */
    @Test
    void fromCommandLine() {
        assertThrows(IllegalArgumentException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{})));
        assertThrows(IllegalArgumentException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{"-ca", "-1"})));
        assertThrows(IllegalArgumentException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{"-ct", "-1"})));
        assertThrows(IllegalArgumentException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{"-f", "1-1-2023", "-t", "1-1-2020"})));
        assertThrows(IllegalArgumentException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{"-oa", "file.xml"})));
        assertThrows(IllegalArgumentException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{"-ot", "file.xml"})));
    }
}