package it.unipd.dei.eis.core.constants;

/**
 * Default settings.
 */
public interface DefaultSettings {

    /**
     * The default number of articles to pull.
     */
    int ARTICLES_COUNT = 10;

    /**
     * The default number of terms to extract.
     */
    int TERMS_COUNT = 50;

    /**
     * The default .json file where to push the articles.
     */
    String JSON_FILE_NAME = "articles.json";

    /**
     * The default .txt file where to push the articles.
     */
    String TXT_FILE_NAME = "terms.txt";
}
