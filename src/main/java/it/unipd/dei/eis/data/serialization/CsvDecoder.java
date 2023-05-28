package it.unipd.dei.eis.data.serialization;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * CsvDecoder is the decoder for CSV data.
 */
public class CsvDecoder implements IDecoder {

    /**
     * The CSV format object used to parse the CSV file.
     */
    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.builder()
            .setSkipHeaderRecord(true)
            .build();

    /**
     * The decode method is used to decode the CSV file.
     *
     * @param data the CSV file
     * @param args the arguments
     * @return the list of CSVRecord
     * @throws IOException if an I/O error occurs
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<CSVRecord> decode(String data, Object... args) throws IOException {
        return CSV_FORMAT.parse(new FileReader(data))
                .getRecords();
    }
}
