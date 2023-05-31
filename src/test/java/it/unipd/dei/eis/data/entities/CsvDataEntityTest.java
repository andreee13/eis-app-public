package it.unipd.dei.eis.data.entities;

import it.unipd.dei.eis.data.serialization.CsvDecoder;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the CsvDataEntity class.
 */
class CsvDataEntityTest {

    /**
     * Test the fromCsvRecord method.
     */
    @Test
    void fromCsvRecord() throws IOException {
        List<CSVRecord> record = new CsvDecoder().decode("file.csv");
        String id = CsvDataEntity.fromCsvRecord(record.get(0)).identifier;
        assertEquals(id, record.get(0)
                .get(0));
    }
}