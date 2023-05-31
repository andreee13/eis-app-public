package it.unipd.dei.eis.domain.models;

import java.util.Date;
import java.util.Objects;

/**
 * Article is the model for the articles.
 * It contains the data structure of the articles.
 */
public class Article implements IModel {

    /**
     * The id field is the id of the article.
     */
    final public String id;

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
     * Article constructor.
     *
     * @param id     the id of the article
     * @param title  the title of the article
     * @param body   the body of the article
     * @param url    the url of the article
     * @param date   the date of the article
     * @param source the source of the article
     */
    public Article(String id, String title, String body, String url, Date date, String source) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.url = url;
        this.date = date;
        this.source = source;
    }

    /**
     * Equals method for the Article class.
     *
     * @param object the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Article other = (Article) object;
        return Objects.equals(id, other.id)
                && Objects.equals(title, other.title)
                && Objects.equals(body, other.body)
                && Objects.equals(url, other.url)
                && Objects.equals(date, other.date)
                && Objects.equals(source, other.source);
    }
}
