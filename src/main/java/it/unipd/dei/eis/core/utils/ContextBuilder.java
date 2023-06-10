package it.unipd.dei.eis.core.utils;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.common.IBuilder;
import it.unipd.dei.eis.core.enums.UseCases;

import java.util.Date;

/**
 * The ContextBuilder class is the builder class of the Context class.
 */
public class ContextBuilder implements IBuilder<Context> {

    /**
     * The useCase field contains the UseCase.
     */
    private UseCases useCase;

    /**
     * The source field contains the source from which the data are retrieved.
     */
    private String source;

    /**
     * The outputArticles field contains the output articles file name.
     */
    private String outputArticles;

    /**
     * The outputTerms field contains the output terms file name.
     */
    private String outputTerms;

    /**
     * The query field contains the query to be searched.
     */
    private String query;

    /**
     * The countArticles field contains the number of articles to be retrieved.
     */
    private int countArticles;

    /**
     * The countTerms field contains the number of terms to be extracted.
     */
    private int countTerms;

    /**
     * The fromDate field contains the date from which the articles are retrieved.
     */
    private Date fromDate;

    /**
     * The toDate field contains the limit date to which the articles are retrieved.
     */
    private Date toDate;

    /**
     * The apiKey field contains the API key.
     */
    private String apiKey;

    /**
     * Sets the command.
     *
     * @param useCase the useCase
     * @return the context builder
     */
    public ContextBuilder setUseCase(UseCases useCase) {
        this.useCase = useCase;
        return this;
    }

    /**
     * Sets the source.
     *
     * @param source the source
     * @return the context builder
     */
    public ContextBuilder setSource(String source) {
        this.source = source;
        return this;
    }

    /**
     * Sets the output articles.
     *
     * @param outputArticles the output articles
     * @return the context builder
     */
    public ContextBuilder setOutputArticles(String outputArticles) {
        this.outputArticles = outputArticles;
        return this;
    }

    /**
     * Sets the output terms.
     *
     * @param outputTerms the output terms
     * @return the context builder
     */
    public ContextBuilder setOutputTerms(String outputTerms) {
        this.outputTerms = outputTerms;
        return this;
    }

    /**
     * Sets the query.
     *
     * @param query the query
     * @return the context builder
     */
    public ContextBuilder setQuery(String query) {
        this.query = query;
        return this;
    }

    /**
     * Sets the count articles.
     *
     * @param countArticles the count articles
     * @return the context builder
     */
    public ContextBuilder setCountArticles(int countArticles) {
        this.countArticles = countArticles;
        return this;
    }

    /**
     * Sets the count terms.
     *
     * @param countTerms the count terms
     * @return the context builder
     */
    public ContextBuilder setCountTerms(int countTerms) {
        this.countTerms = countTerms;
        return this;
    }

    /**
     * Sets the from date.
     *
     * @param fromDate the from date
     * @return the context builder
     */
    public ContextBuilder setFromDate(Date fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * Sets the to date.
     *
     * @param toDate the to date
     * @return the context builder
     */
    public ContextBuilder setToDate(Date toDate) {
        this.toDate = toDate;
        return this;
    }

    /**
     * Sets the api key.
     *
     * @param apiKey the shared data
     * @return the context builder
     */
    public ContextBuilder setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    /**
     * Builds the context.
     *
     * @return the context
     */
    @Override
    public Context build() {
        return new Context(
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