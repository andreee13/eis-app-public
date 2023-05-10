package it.unipd.dei.eis.data.entities;

import org.apache.commons.csv.CSVRecord;

import java.util.Date;

public class CsvDataEntity implements IDataEntity {
    public final String identifier;
    public final String url;
    public final String title;
    public final String fullText;
    public final Date date;
    public final String sourceSet;
    public final String source;

    public CsvDataEntity(String identifier, String url, String title, String fullText, Date date, String sourceSet, String source) {
        this.identifier = identifier;
        this.url = url;
        this.title = title;
        this.fullText = fullText;
        this.date = date;
        this.sourceSet = sourceSet;
        this.source = source;
    }

    public static CsvDataEntity fromCsvRecord(CSVRecord record) {
        return new CsvDataEntity(
                record.get("identifier"),
                record.get("url"),
                record.get("title"),
                record.get("full_text"),
                new Date(Long.parseLong(record.get("date"))),
                record.get("source_set"),
                record.get("source")
        );
    }
}
