package it.unipd.dei.eis.core.constants;

/**
 * The Outputs interface contains the output constants and methods.
 */
public interface Outputs {

    /**
     * The ARTICLE_OUTPUTS field contains the article outputs.
     */
    String[] ARTICLES_OUTPUTS = new String[]{
            ".json",
    };

    /**
     * The TERM_OUTPUTS field contains the term outputs.
     */
    String[] TERMS_OUTPUTS = new String[]{
            ".txt",
    };

    /**
     * Checks if the output is an article output.
     *
     * @param output the output
     * @return true if the output is a valid articles output, false otherwise
     */
    static boolean isArticlesOutput(String output) {
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
    static boolean isTermsOutput(String output) {
        for (String termOutput : TERMS_OUTPUTS) {
            if (output.endsWith(termOutput)) {
                return true;
            }
        }
        return false;
    }
}
