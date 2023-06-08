package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.data.codecs.CsvDecoder;
import it.unipd.dei.eis.data.entities.CsvDataEntity;
import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * CsvDataSource is the data source for CSV records.
 * It contains the data structure of the CSV response.
 */
public class CsvDataSource extends DataSource<CsvDataEntity, List<CSVRecord>> {

    /**
     * The ID of the data source.
     */
    public static final String ID = "CSV";

    /**
     * CsvDataSource constructor.
     */
    public CsvDataSource() {
        super(ID, new CsvDecoder());
    }

    /**
     * Returns the list of entities from the CSV data.
     *
     * @param context the context of the request
     * @return the list of articles
     * @throws Exception if an error occurs
     */
    @Override
    List<CsvDataEntity> getData(Context context) throws Exception {
        List<CsvDataEntity> data = Objects.requireNonNull(decoder)
                .decode(context.source).stream()
                .map(CsvDataEntity::fromCsvRecord)
                .collect(Collectors.toList());
        return data.subList(1, data.size());
    }
}
