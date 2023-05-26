package it.unipd.dei.eis.data.entities;

/**
 * ArticleTermsDataEntity.
 */
public class ArticleTermsDataEntity implements IDataEntity {

    /**
     * The title of the article.
     */
    final public String title;

    /**
     * The body of the article.
     */
    final public String body;

    /**
     * Constructs an ArticleTermsDataEntity object.
     *
     * @param title the title of the article
     * @param body the body of the article
     */
    public ArticleTermsDataEntity(String title, String body) {
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
