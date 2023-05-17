package it.unipd.dei.eis.presentation;

import it.unipd.dei.eis.domain.controllers.ControllerFactory;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Bootstrapper {
    private final String[] args;

    public Bootstrapper(String[] args) {
        this.args = args;
    }

    public void launch() {
        Options options = new Options();
        options.addOption(Option.builder().option("h").longOpt("help").desc("Print this message").build());
        options.addOption(Option.builder().option("s").longOpt("source").desc("Data source").hasArg().argName("source|file").required().build());
        options.addOption(Option.builder().option("o").longOpt("output").desc("Output file name").hasArg().argName("file").build());
        options.addOption(Option.builder().option("q").longOpt("query").desc("Search query").hasArg().argName("string").build());
        options.addOption(Option.builder().option("c").longOpt("count").desc("Number of results").hasArg().argName("integer").type(Number.class).build());
        options.addOption(Option.builder().option("f").longOpt("from").desc("From date").hasArg().argName("date").build());
        options.addOption(Option.builder().option("t").longOpt("to").desc("To date").hasArg().argName("date").build());
        try {
            Context context = new Context(new DefaultParser().parse(options, args));
            ControllerFactory.create(context.command).run(context);
        } catch (Exception e) {
            new HelpFormatter().printHelp("java -jar <jarfile> <download|extract|all> [options]", options);
        }
    }
}
