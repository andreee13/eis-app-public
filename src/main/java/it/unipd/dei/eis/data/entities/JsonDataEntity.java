package it.unipd.dei.eis.data.entities;

import java.util.Date;

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
}

