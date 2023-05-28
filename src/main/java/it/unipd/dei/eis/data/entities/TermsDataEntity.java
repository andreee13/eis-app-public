package it.unipd.dei.eis.data.entities;

/**
 * TermsDataEntity.
 */
public class TermsDataEntity implements IDataEntity {

    /**
     * The title of the article.
     */
    final public String title;

    /**
     * The body of the article.
     */
    final public String body;

    /**
     * Constructs an TermsDataEntity object.
     *
     * @param title the title of the article
     * @param body  the body of the article
     */
    public TermsDataEntity(String title, String body) {
        this.title = title;
        this.body = body;
    }

    /**
     * Returns a formatted string.
     *
     * @return a formatted string
     */
    @Override
    public String toString() {
        return String.format("%s %s", title, body);
    }
}
