package it.unipd.dei.eis.core.constants;

/**
 * The Outputs class contains the outputs constants.
 */
public abstract class Outputs {

    /**
     * The ARTICLE_OUTPUTS field contains the article outputs.
     */
    public static final String[] ARTICLES_OUTPUTS = new String[]{
            ".json",
    };

    /**
     * The TERM_OUTPUTS field contains the term outputs.
     */
    public static final String[] TERMS_OUTPUTS = new String[]{
            ".txt",
    };

    /**
     * Checks if the output is an article output.
     *
     * @param output the output
     * @return true if the output is an valid articles output, false otherwise
     */
    public static boolean isArticlesOutput(String output) {
        for (String articleOutput : ARTICLES_OUTPUTS) {
            if (output.endsWith(articleOutput)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the output is a term output.
     *
     * @param output the output
     * @return true if the output is a valid terms output, false otherwise
     */
    public static boolean isTermsOutput(String output) {
        for (String termOutput : TERMS_OUTPUTS) {
            if (output.endsWith(termOutput)) {
                return true;
            }
        }
        return false;
    }
}
