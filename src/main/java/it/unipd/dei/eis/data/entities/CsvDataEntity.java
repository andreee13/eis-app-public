package it.unipd.dei.eis.data.entities;

import it.unipd.dei.eis.core.utils.DateParser;
import org.apache.commons.csv.CSVRecord;

import java.util.Date;

/**
 * A data entity that represents a CSV record.
 */
public class CsvDataEntity extends DataEntity {

    /**
     * The identifier of the record.
     */
    public final String identifier;

    /**
     * The URL of the record.
     */
    public final String url;

    /**
     * The title of the record.
     */
    public final String title;

    /**
     * The body of the record.
     */
    public final String body;

    /**
     * The date of the record.
     */
    public final Date date;

    /**
     * The source set of the record.
     */
    public final String sourceSet;

    /**
     * The source of the record.
     */
    public final String source;

    /**
     * Creates a new CSV data entity.
     *
     * @param identifier the identifier of the record
     * @param url        the URL of the record
     * @param title      the title of the record
     * @param body       the body of the record
     * @param date       the date of the record
     * @param sourceSet  the source set of the record
     * @param source     the source of the record
     */
    public CsvDataEntity(String identifier, String url, String title, String body, Date date, String sourceSet, String source) {
        this.identifier = identifier;
        this.url = url;
        this.title = title;
        this.body = body;
        this.date = date;
        this.sourceSet = sourceSet;
        this.source = source;
    }

    /**
     * Creates a new CSV data entity from a CSV record.
     *
     * @param record the CSV record
     * @return the CSV data entity
     */
    public static CsvDataEntity fromCsvRecord(CSVRecord record) {
        return new CsvDataEntity(
                record.get(0),
                record.get(1),
                record.get(2),
                record.get(3),
                DateParser.tryParse(record.get(4)),
                record.get(5),
                record.get(6)
        );
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
        return this.date != null && this.date.before(date);
    }

    /**
     * Filters the object by a date.
     *
     * @param date the date to filter to
     * @return true if the object respects the filter, false otherwise
     */
    @Override
    public boolean after(Date date) {
        return this.date != null && this.date.after(date);
    }
}