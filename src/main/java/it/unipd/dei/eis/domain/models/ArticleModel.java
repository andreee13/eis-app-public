package it.unipd.dei.eis.domain.models;

import java.util.Date;
import java.util.Objects;

/**
 * ArticleModel is the model for the articles.
 * It contains the data structure of the articles.
 */
public class ArticleModel implements IModel {

    /**
     * The id field is the id of the article.
     */
    final public int id;

    /**
     * The title field is the title of the article.
     */
    final public String title;

    /**
     * The body field is the body of the article.
     */
    final public String body;

    /**
     * The url field is the url of the article.
     */
    final public String url;

    /**
     * The date field is the date of the article.
     */
    final public Date date;

    /**
     * The source field is the source of the article.
     */
    final public String source;

    /**
     * ArticleModel constructor.
     *
     * @param title  the title of the article
     * @param body   the body of the article
     * @param url    the url of the article
     * @param date   the date of the article
     * @param source the source of the article
     */
    public ArticleModel(String title, String body, String url, Date date, String source) {
        this.id = Objects.hash(title, body, url, date, source);
        this.title = title;
        this.body = body;
        this.url = url;
        this.date = date;
        this.source = source;
    }
}
