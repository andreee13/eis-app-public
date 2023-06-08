package it.unipd.dei.eis.data.codecs;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * CsvDecoder is the decoder for CSV data.
 */
public class CsvDecoder implements IDecoder<List<CSVRecord>> {

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
     * @return the list of CSV Records
     * @throws IOException if an I/O error occurs
     */
    @Override
    public List<CSVRecord> decode(String data, Object... args) throws IOException {
        try (FileReader fileReader = new FileReader(data)) {
            return CSV_FORMAT.parse(fileReader)
                    .getRecords();
        }
    }
}
