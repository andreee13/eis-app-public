package it.unipd.dei.eis.presentation;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.domain.use_cases.UseCaseFactory;
import org.apache.commons.cli.*;

/**
 * Bootstrapper is the class that starts the application.
 * It parses the command line arguments and calls the correct use case.
 */
public class Bootstrapper {

    /**
     * The OPTIONS field contains the command line options.
     */
    private static final Options OPTIONS = new Options();

    /**
     * The PARSER field contains the command line parser.
     */
    private static final DefaultParser PARSER = new DefaultParser();

    /**
     * The HELP_MESSAGE field contains the help message.
     */
    private static final String HELP_MESSAGE = "java -jar <jarfile> [options]";

    static {
        OPTIONS.addOption(Option.builder()
                .option("h")
                .longOpt("help")
                .desc("Print this message")
                .build());
        OPTIONS.addOption(Option.builder()
                .option("d")
                .longOpt("download")
                .desc("Download only")
                .build());
        OPTIONS.addOption(Option.builder()
                .option("e")
                .longOpt("extract")
                .desc("Extract only")
                .build());
        OPTIONS.addOption(Option.builder()
                .option("oa")
                .longOpt("output-articles")
                .desc("Output articles file name")
                .hasArg()
                .argName("file")
                .build());
        OPTIONS.addOption(Option.builder()
                .option("ot")
                .longOpt("output-terms")
                .desc("Output terms file name")
                .hasArg()
                .argName("file")
                .build());
        OPTIONS.addOption(Option.builder()
                .option("q")
                .longOpt("query")
                .desc("Search query")
                .hasArg()
                .argName("string")
                .build());
        OPTIONS.addOption(Option.builder()
                .option("ct")
                .longOpt("count-terms")
                .desc("Number of terms")
                .hasArg()
                .argName("integer")
                .type(Number.class)
                .build());
        OPTIONS.addOption(Option.builder()
                .option("ca")
                .longOpt("count-articles")
                .desc("Number of articles")
                .hasArg()
                .argName("integer")
                .type(Number.class)
                .build());
        OPTIONS.addOption(Option.builder()
                .option("f")
                .longOpt("from")
                .desc("From date")
                .hasArg()
                .argName("date")
                .build());
        OPTIONS.addOption(Option.builder()
                .option("t")
                .longOpt("to")
                .desc("To date")
                .hasArg()
                .argName("date")
                .build());
        OPTIONS.addOption(Option.builder()
                .option("k")
                .longOpt("api-key")
                .desc("API key")
                .hasArg()
                .argName("string")
                .build());
    }

    /**
     * The args field contains the command line arguments.
     */
    private final String[] args;

    /**
     * The Bootstrapper constructor.
     *
     * @param args the command line arguments
     */
    public Bootstrapper(String[] args) {
        this.args = args;
    }

    /**
     * The launch method parses the command line arguments and calls the correct use case.
     */
    public void launch() {
        try {
            Context context = Context.fromCommandLine(PARSER.parse(OPTIONS, args));
            UseCaseFactory.create(context.useCase)
                    .run(context);
        } catch (ParseException | IllegalArgumentException e) {
            new HelpFormatter().printHelp(
                    HELP_MESSAGE,
                    OPTIONS
            );
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
