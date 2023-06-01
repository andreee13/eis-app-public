package it.unipd.dei.eis.core.common;

import it.unipd.dei.eis.core.constants.DefaultConfig;
import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.core.utils.IntegerParser;
import org.apache.commons.cli.CommandLine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     * The output field contains the articles file name.
     */
    public final String outputArticles;

    /**
     * The output field contains the terms file name.
     */
    public final String outputTerms;

    /**
     * The query field contains the query to be searched.
     */
    public final String query;

    /**
     * The countArticles field contains the number of articles to be retrieved.
     */
    public final int countArticles;

    /**
     * The countTerms field contains the number of terms to be extracted.
     */
    public final int countTerms;

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
     * The sharedData field contains the shared data during the execution.
     */
    public final Map<String, Object> sharedData;

    /**
     * The Context constructor.
     *
     * @param command        the command
     * @param source         the source
     * @param outputArticles the output articles file name
     * @param outputTerms    the output terms file name
     * @param query          the query
     * @param countArticles  the number of articles to be retrieved
     * @param countTerms     the number of terms to be extracted
     * @param fromDate       the date from which the articles are retrieved
     * @param toDate         the limit date to which the articles are retrieved
     * @param apiKey         the API key
     * @param sharedData     the shared data during the execution
     */
    public Context(String command, String source, String outputArticles, String outputTerms, String query, int countArticles, int countTerms, Date fromDate, Date toDate, String apiKey, Map<String, Object> sharedData) {
        this.command = command;
        this.source = source;
        this.outputArticles = outputArticles;
        this.outputTerms = outputTerms;
        this.query = query;
        this.countArticles = countArticles;
        this.countTerms = countTerms;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.apiKey = apiKey;
        this.sharedData = sharedData;
    }

    /**
     * The Context constructor.
     * It is used to parse the command line.
     *
     * @param cmd the command line
     */
    public Context(CommandLine cmd) {
        command = cmd.getArgs()[0];
        source = cmd.getOptionValue("source");
        outputArticles = cmd.getOptionValue("output-articles") == null ? DefaultConfig.JSON_FILE_NAME : cmd.getOptionValue("output-articles");
        outputTerms = cmd.getOptionValue("output-terms") == null ? DefaultConfig.TXT_FILE_NAME : cmd.getOptionValue("output-terms");
        query = cmd.getOptionValue("query");
        Integer ca = IntegerParser.tryParse(cmd.getOptionValue("count-articles"));
        countArticles = ca == null ? DefaultConfig.ARTICLES_COUNT : ca;
        Integer ct = IntegerParser.tryParse(cmd.getOptionValue("count-terms"));
        countTerms = ct == null ? DefaultConfig.TERMS_COUNT : ct;
        fromDate = DateParser.tryParse(cmd.getOptionValue("from"));
        toDate = DateParser.tryParse(cmd.getOptionValue("to"));
        apiKey = cmd.getOptionValue("api-key");
        sharedData = new HashMap<>();
    }
}
