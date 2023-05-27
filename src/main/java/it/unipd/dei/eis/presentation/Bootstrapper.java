package it.unipd.dei.eis.presentation;

import it.unipd.dei.eis.domain.use_cases.UseCaseFactory;
import org.apache.commons.cli.*;

/**
 * Bootstrapper is the class that starts the application.
 * It parses the command line arguments and calls the correct use case.
 */
public class Bootstrapper {

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
        Options options = new Options();
        options.addOption(Option.builder()
                .option("h")
                .longOpt("help")
                .desc("Print this message")
                .build());
        options.addOption(Option.builder()
                .option("s")
                .longOpt("source")
                .desc("Data source")
                .hasArg()
                .argName("source|file")
                .required()
                .build());
        options.addOption(Option.builder()
                .option("o")
                .longOpt("output")
                .desc("Output file name")
                .hasArg()
                .argName("file")
                .build());
        options.addOption(Option.builder()
                .option("q")
                .longOpt("query")
                .desc("Search query")
                .hasArg()
                .argName("string")
                .build());
        options.addOption(Option.builder()
                .option("ct")
                .longOpt("count-terms")
                .desc("Number of terms")
                .hasArg()
                .argName("integer")
                .type(Number.class)
                .build());
        options.addOption(Option.builder()
                .option("ca")
                .longOpt("count-articles")
                .desc("Number of articles")
                .hasArg()
                .argName("integer")
                .type(Number.class)
                .build());
        options.addOption(Option.builder()
                .option("f")
                .longOpt("from")
                .desc("From date")
                .hasArg()
                .argName("date")
                .build());
        options.addOption(Option.builder()
                .option("t")
                .longOpt("to")
                .desc("To date")
                .hasArg()
                .argName("date")
                .build());
        options.addOption(Option.builder()
                .option("k")
                .longOpt("api-key")
                .desc("API key")
                .hasArg()
                .argName("string")
                .build());
        try {
            Context context = new Context(new DefaultParser().parse(options, args));
            UseCaseFactory.create(context.command)
                    .run(context);
        } catch (ParseException e) {
            new HelpFormatter().printHelp("java -jar <jarfile> <download|extract|both> [options]", options);
        }
    }
}
