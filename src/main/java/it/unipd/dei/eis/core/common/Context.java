package it.unipd.dei.eis.core.common;

import it.unipd.dei.eis.core.constants.DefaultConfig;
import it.unipd.dei.eis.core.constants.UseCases;
import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.core.utils.IntegerParser;
import org.apache.commons.cli.CommandLine;

import java.util.Date;
import java.util.Objects;

/**
 * The Context class contains the context of the command line.
 */
public class Context {

    /**
     * The useCase field contains the UseCase.
     */
    public final UseCases useCase;

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
     * The Context constructor.
     *
     * @param useCase        the useCase
     * @param source         the source
     * @param outputArticles the output articles file name
     * @param outputTerms    the output terms file name
     * @param query          the query
     * @param countArticles  the number of articles to be retrieved
     * @param countTerms     the number of terms to be extracted
     * @param fromDate       the date from which the articles are retrieved
     * @param toDate         the limit date to which the articles are retrieved
     * @param apiKey         the API key
     */
    public Context(
            UseCases useCase,
            String source,
            String outputArticles,
            String outputTerms,
            String query,
            int countArticles,
            int countTerms,
            Date fromDate,
            Date toDate,
            String apiKey
    ) {
        this.useCase = useCase;
        this.source = source;
        this.outputArticles = outputArticles;
        this.outputTerms = outputTerms;
        this.query = query;
        this.countArticles = countArticles;
        this.countTerms = countTerms;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.apiKey = apiKey;
    }

    /**
     * The Context factory constructor.
     * It is used to parse the command line.
     *
     * @param cmd the command line
     * @return the Context
     */
    public static Context fromCommandLine(CommandLine cmd) {
        if (cmd.getArgs().length == 0) {
            throw new IllegalArgumentException("The source option is required");
        }
        Integer ca = IntegerParser.tryParse(cmd.getOptionValue("count-articles"));
        if (ca != null && ca < 0) {
            throw new IllegalArgumentException("The count-articles option must be a positive integer");
        }
        Integer ct = IntegerParser.tryParse(cmd.getOptionValue("count-terms"));
        if (ct != null && ct < 0) {
            throw new IllegalArgumentException("The count-terms option must be a positive integer");
        }
        Date from = DateParser.tryParse(cmd.getOptionValue("from"));
        Date to = DateParser.tryParse(cmd.getOptionValue("to"));
        if (from != null && to != null && from.after(to)) {
            throw new IllegalArgumentException("The from-date option must be before the to-date option");
        }
        String oa = cmd.getOptionValue("output-articles");
        String ot = cmd.getOptionValue("output-terms");
        return new Context(
                UseCases.fromCommandLine(cmd),
                cmd.getArgs()[0],
                oa == null ? DefaultConfig.JSON_FILE_NAME : oa,
                ot == null ? DefaultConfig.TXT_FILE_NAME : ot,
                cmd.getOptionValue("query"),
                ca == null ? DefaultConfig.ARTICLES_COUNT : ca,
                ct == null ? DefaultConfig.TERMS_COUNT : ct,
                DateParser.tryParse(cmd.getOptionValue("from")),
                DateParser.tryParse(cmd.getOptionValue("to")),
                cmd.getOptionValue("api-key")
        );
    }

    /**
     * The Context hashCode method.
     * It is used to compare two Context objects.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                useCase,
                source,
                outputArticles,
                outputTerms,
                query,
                countArticles,
                countTerms,
                fromDate,
                toDate,
                apiKey
        );
    }
}
