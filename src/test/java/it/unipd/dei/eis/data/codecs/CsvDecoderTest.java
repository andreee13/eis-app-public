package it.unipd.dei.eis.data.codecs;

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
     *
     * @throws IOException if an error occurs.
     */
    @Test
    void decode() throws IOException {
        List<CSVRecord> records = new CsvDecoder().decode("src/test/resources/file.csv");
        assertTrue(records.size() > 0);
    }
}