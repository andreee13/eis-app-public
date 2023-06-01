package it.unipd.dei.eis.data.entities;

import java.util.Date;

/**
 * A data entity that represents a JSON record.
 */
public class JsonDataEntity extends DataEntity {

    /**
     * The identifier of the record.
     */
    final public int id;

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
     * @param title  the title of the record
     * @param body   the body of the record
     * @param url    the URL of the record
     * @param date   the date of the record
     * @param source the source of the record
     */
    public JsonDataEntity(int id, String title, String body, String url, Date date, String source) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.url = url;
        this.date = date;
        this.source = source;
    }

    /**
     * Filters the object by a string.
     *
     * @param s the string to filter by
     * @return true if the object respects the filter, false otherwise
     */
    @Override
    public boolean contains(String s) {
        return title.contains(s) || body.contains(s);
    }

    /**
     * Filters the object by a date.
     *
     * @param date the date to filter from
     * @return true if the object respects the filter, false otherwise
     */
    @Override
    public boolean before(Date date) {
        return this.date.before(date);
    }

    /**
     * Filters the object by a date.
     *
     * @param date the date to filter to
     * @return true if the object respects the filter, false otherwise
     */
    @Override
    public boolean after(Date date) {
        return this.date.after(date);
    }
}

