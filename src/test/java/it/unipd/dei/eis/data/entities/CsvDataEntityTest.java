package it.unipd.dei.eis.data.entities;

import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.data.codecs.CsvDecoder;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the CsvDataEntity class.
 */
class CsvDataEntityTest {

    /**
     * A sample CSV data entity.
     */
    private final CsvDataEntity entity = new CsvDataEntity(
            "identifier",
            "url",
            "title",
            "body",
            DateParser.tryParse("2023-01-01"),
            "sourceSet",
            "source"
    );

    /**
     * Test the fromCsvRecord method.
     *
     * @throws IOException if an error occurs.
     */
    @Test
    void fromCsvRecord() throws IOException {
        List<CSVRecord> record = new CsvDecoder().decode("src/test/resources/file.csv");
        String id = CsvDataEntity.fromCsvRecord(record.get(0)).identifier;
        assertEquals(id, record.get(0).get(0));
    }

    /**
     * Test the contains method.
     */
    @Test
    void contains() {
        assertTrue(entity.contains("title"));
    }

    /**
     * Test the before method.
     */
    @Test
    void before() {
        assertTrue(entity.before(DateParser.tryParse("2023-01-02")));
    }

    /**
     * Test the after method.
     */
    @Test
    void after() {
        assertTrue(entity.after(DateParser.tryParse("2022-12-31")));
    }
}
