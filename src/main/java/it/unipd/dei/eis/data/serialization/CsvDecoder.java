package it.unipd.dei.eis.data.serialization;

import it.unipd.dei.eis.data.entities.CsvDataEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDecoder implements IDecoder {
    @Override
    @SuppressWarnings("unchecked")
    public List<CsvDataEntity> decode(String data, Object... args) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setSkipHeaderRecord(true).build();
        Iterable<CSVRecord> records = csvFormat.parse(new FileReader((String) args[0]));
        List<CsvDataEntity> entities = new ArrayList<>();
        for (CSVRecord record : records) {
            entities.add(CsvDataEntity.fromCsvRecord(record));
        }
        return entities;
    }
}
