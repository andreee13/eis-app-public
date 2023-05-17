package it.unipd.dei.eis.data.entities;

import it.unipd.dei.eis.core.utils.DateParser;
import org.apache.commons.csv.CSVRecord;

import java.util.Date;

public class CsvDataEntity implements IDataEntity {
    public final String identifier;
    public final String url;
    public final String title;
    public final String body;
    public final Date date;
    public final String sourceSet;
    public final String source;

    public CsvDataEntity(String identifier, String url, String title, String body, Date date, String sourceSet, String source) {
        this.identifier = identifier;
        this.url = url;
        this.title = title;
        this.body = body;
        this.date = date;
        this.sourceSet = sourceSet;
        this.source = source;
    }

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
}
