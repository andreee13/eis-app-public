package it.unipd.dei.eis.core.common;

import it.unipd.dei.eis.presentation.Bootstrapper;
import org.apache.commons.cli.ParseException;
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
        assertThrows(ParseException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{})));
        assertThrows(ParseException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{"file.json", "-ca", "-1"})));
        assertThrows(ParseException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{"file.json", "-ct", "-1"})));
        assertThrows(ParseException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{"file.json", "-f", "1-1-2023", "-t", "1-1-2020"})));
        assertThrows(ParseException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{"file.json", "-oa", "file.xml"})));
        assertThrows(ParseException.class, () -> Context.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, new String[]{"file.json", "-ot", "file.xml"})));
    }
}