package it.unipd.dei.eis.core.enums;

import it.unipd.dei.eis.presentation.Bootstrapper;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Test the UseCases class.
 */
class UseCasesTest {

    /**
     * Test the fromCommandLine method.
     *
     * @throws ParseException if the parsing fails
     */
    @Test
    void fromCommandLine() throws ParseException {
        String[] args1 = new String[]{"-d"};
        assertSame(UseCases.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, args1)), UseCases.DOWNLOAD);
        String[] args2 = new String[]{"-e"};
        assertSame(UseCases.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, args2)), UseCases.EXTRACT);
        String[] args3 = new String[]{"-d", "-e"};
        assertSame(UseCases.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, args3)), UseCases.DOWNLOAD_AND_EXTRACT);
        String[] args4 = new String[]{};
        assertSame(UseCases.fromCommandLine(Bootstrapper.PARSER.parse(Bootstrapper.OPTIONS, args4)), UseCases.DOWNLOAD_AND_EXTRACT);
    }
}