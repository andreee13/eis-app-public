package it.unipd.dei.eis.presentation;

import it.unipd.dei.eis.domain.controllers.ControllerFactory;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class Bootstrapper {
    private final String[] args;

    public Bootstrapper(String[] args) {
        this.args = args;
    }

    public void launch() {
        Options options = new Options();
        options.addOption("h", "help", false, "Print this message");
        options.addOption("o", "output", true, "Output file");
        options.addOption("q", "query", true, "Query");
        options.addOption("c", "count", true, "Number of results");
        options.addOption("s", "source", true, "Source");
        options.addOption("f", "from", true, "From date");
        options.addOption("t", "to", true, "To date");
        try {
            CommandLine cmd = new DefaultParser().parse(options, args);
            ControllerFactory.create(args[0], cmd).run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
