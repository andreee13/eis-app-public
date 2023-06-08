package it.unipd.dei.eis.core.constants;

/**
 * Default configuration values.
 */
public abstract class DefaultConfig {

    /**
     * The default number of articles to pull.
     */
    public static final int ARTICLES_COUNT = 10;

    /**
     * The default number of terms to extract.
     */
    public static final int TERMS_COUNT = 50;

    /**
     * The default .json file where to push the articles.
     */
    public static final String JSON_FILE_NAME = "articles.json";

    /**
     * The default .txt file where to push the articles.
     */
    public static final String TXT_FILE_NAME = "terms.txt";
}
