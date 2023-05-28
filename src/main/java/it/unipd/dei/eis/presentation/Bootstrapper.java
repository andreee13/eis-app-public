package it.unipd.dei.eis.presentation;

import it.unipd.dei.eis.core.constants.UseCaseConstants;
import it.unipd.dei.eis.domain.use_cases.UseCaseFactory;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

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

    static {
        OPTIONS.addOption(Option.builder()
                .option("h")
                .longOpt("help")
                .desc("Print this message")
                .build());
        OPTIONS.addOption(Option.builder()
                .option("s")
                .longOpt("source")
                .desc("Data source (required)")
                .hasArg()
                .argName("source|file")
                .required()
                .build());
        OPTIONS.addOption(Option.builder()
                .option("o")
                .longOpt("output")
                .desc("Output file name")
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
            Context context = new Context(PARSER.parse(OPTIONS, args));
            UseCaseFactory.create(context.command)
                    .run(context);
        } catch (Exception e) {
            new HelpFormatter().printHelp(
                    String.format(
                            "java -jar <jarfile> <%s|%s|%s> [options]",
                            UseCaseConstants.DOWNLOAD,
                            UseCaseConstants.EXTRACT,
                            UseCaseConstants.BOTH
                    ),
                    OPTIONS
            );
        }
    }
}
