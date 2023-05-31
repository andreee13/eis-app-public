package it.unipd.dei.eis.data.entities;

import java.util.Date;
import java.util.Objects;

/**
 * A data entity that represents a JSON record.
 */
public class JsonDataEntity implements IDataEntity {

    /**
     * The identifier of the record.
     */
    final public String id;

    /**
     * The title of the record.
     */
    final public String title;

    /**
     * The body of the record.
     */
    final public String body;

    /**
     * The URL of the record.
     */
    final public String url;

    /**
     * The date of the record.
     */
    final public Date date;

    /**
     * The source of the record.
     */
    final public String source;

    /**
     * Creates a new JSON data entity.
     *
     * @param id     the identifier of the record
     * @param title  the title of the record
     * @param body   the body of the record
     * @param url    the URL of the record
     * @param date   the date of the record
     * @param source the source of the record
     */
    public JsonDataEntity(String id, String title, String body, String url, Date date, String source) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.url = url;
        this.date = date;
        this.source = source;
    }

    /**
     * Equals method for JsonDataEntity
     *
     * @param object Object to compare
     * @return true if the two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        JsonDataEntity that = (JsonDataEntity) object;
        return Objects.equals(id, that.id)
                && Objects.equals(title, that.title)
                && Objects.equals(body, that.body)
                && Objects.equals(url, that.url)
                && Objects.equals(date, that.date)
                && Objects.equals(source, that.source);
    }
}

