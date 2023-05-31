package it.unipd.dei.eis.presentation;

import org.apache.commons.cli.CommandLine;

import java.util.Date;

public class ContextBuilder {
    private String command;
    private String source;
    private String output;
    private String query;
    private int countArticles;
    private int countTerms;
    private Date fromDate;
    private Date toDate;
    private String apiKey;
    private CommandLine cmd;

    public ContextBuilder setCommand(String command) {
        this.command = command;
        return this;
    }

    public ContextBuilder setSource(String source) {
        this.source = source;
        return this;
    }

    public ContextBuilder setOutput(String output) {
        this.output = output;
        return this;
    }

    public ContextBuilder setQuery(String query) {
        this.query = query;
        return this;
    }

    public ContextBuilder setCountArticles(int countArticles) {
        this.countArticles = countArticles;
        return this;
    }

    public ContextBuilder setCountTerms(int countTerms) {
        this.countTerms = countTerms;
        return this;
    }

    public ContextBuilder setFromDate(Date fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public ContextBuilder setToDate(Date toDate) {
        this.toDate = toDate;
        return this;
    }

    public ContextBuilder setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public Context build() {
        return new Context(command, source, output, query, countArticles, countTerms, fromDate, toDate, apiKey);
    }
}