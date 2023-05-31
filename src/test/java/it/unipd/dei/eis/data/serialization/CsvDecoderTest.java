package it.unipd.dei.eis.data.serialization;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the CsvDecoder class.
 */
class CsvDecoderTest {

    /**
     * Test the decode method.
     */
    @Test
    void decode() throws IOException {
        List<CSVRecord> records = new CsvDecoder().decode("file.csv");
        assertTrue(records.size() > 0);
    }
}