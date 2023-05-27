package it.unipd.dei.eis.presentation;

import it.unipd.dei.eis.core.constants.DefaultSettings;
import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.core.utils.IntegerParser;
import org.apache.commons.cli.CommandLine;

import java.util.Date;

/**
 * The Context class contains the context of the command line.
 */
public class Context {

    /**
     * The command field contains the command.
     */
    public final String command;

    /**
     * The source field contains the source from which the data are retrieved.
     */
    public final String source;

    /**
     * The output field contains the output file.
     */
    public final String output;

    /**
     * The query field contains the query to be searched.
     */
    public final String query;

    /**
     * The countArticles field contains the number of articles to be retrieved.
     */
    public final Integer countArticles;

    /**
     * The countTerms field contains the number of terms to be extracted.
     */
    public final Integer countTerms;

    /**
     * The fromDate field contains the date from which the articles are retrieved.
     */
    public final Date fromDate;

    /**
     * The toDate field contains the limit date to which the articles are retrieved.
     */
    public final Date toDate;

    /**
     * The apiKey field contains the API key.
     */
    public final String apiKey;

    /**
     * The Context constructor.
     * It is used to parse the command line.
     *
     * @param cmd the command line
     */
    public Context(CommandLine cmd) {
        this.command = cmd.getArgs()[0];
        source = cmd.getOptionValue("source");
        output = cmd.getOptionValue("output");
        query = cmd.getOptionValue("query");
        Integer ca = IntegerParser.tryParse(cmd.getOptionValue("count-articles"));
        countArticles = ca == null ? DefaultSettings.ARTICLES_COUNT : ca;
        Integer ct = IntegerParser.tryParse(cmd.getOptionValue("count-terms"));
        countTerms = ct == null ? DefaultSettings.TERMS_COUNT : ct;
        fromDate = DateParser.tryParse(cmd.getOptionValue("from"));
        toDate = DateParser.tryParse(cmd.getOptionValue("to"));
        apiKey = cmd.getOptionValue("api-key");
    }
}
