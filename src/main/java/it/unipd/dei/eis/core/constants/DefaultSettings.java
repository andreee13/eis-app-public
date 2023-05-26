package it.unipd.dei.eis.core.constants;

/**
 * Default settings.
 */
public interface DefaultSettings {

    /**
     * The default number of articles to pull.
     */
    Integer ARTICLES_COUNT = 10;

    /**
     * The default number of terms to extract.
     */
    Integer TERMS_COUNT = 50;

    /**
     * The default .json file where to push the articles.
     */
    String OUTPUT_FILE_JSON = "articles.json";

    /**
     * The default .txt file where to push the articles.
     */
    String OUTPUT_FILE_TXT = "terms.txt";
}
