package it.unipd.dei.eis.presentation;

import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.core.utils.IntegerParser;
import org.apache.commons.cli.CommandLine;

import java.util.Date;

public class Context {
    public final String command;
    public final String source;
    public final String output;
    public final String query;
    public final Integer count;
    public final Date fromDate;
    public final Date toDate;

    public Context(CommandLine cmd) {
        this.command = cmd.getArgs()[0];
        source = cmd.getOptionValue("source");
        output = cmd.getOptionValue("output");
        query = cmd.getOptionValue("query");
        count = IntegerParser.tryParse(cmd.getOptionValue("count"));
        fromDate = DateParser.tryParse(cmd.getOptionValue("from"));
        toDate = DateParser.tryParse(cmd.getOptionValue("to"));
    }
}
