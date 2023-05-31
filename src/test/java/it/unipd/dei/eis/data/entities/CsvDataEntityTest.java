package it.unipd.dei.eis.data.entities;

import it.unipd.dei.eis.data.serialization.CsvDecoder;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvDataEntityTest {

    @Test
    void fromCsvRecord() throws IOException {
        List<CSVRecord> record = new CsvDecoder().decode("file.csv");
        assertTrue(record.size() > 0);
        //TODO
    }
}