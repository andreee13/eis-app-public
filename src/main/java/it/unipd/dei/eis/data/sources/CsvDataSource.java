package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.data.entities.CsvDataEntity;
import it.unipd.dei.eis.data.serialization.CsvDecoder;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CsvDataSource is the data source for CSV records.
 * It contains the data structure of the CSV response.
 */
public class CsvDataSource extends DataSource<CsvDataEntity> {

    /**
     * The ID of the data source.
     */
    private static final String ID = "CSV";

    /**
     * CsvDataSource constructor.
     */
    public CsvDataSource() {
        super(ID, new CsvDecoder());
    }

    /**
     * Returns the list of articles.
     *
     * @param context the context of the request
     * @return the list of articles
     * @throws Exception if an error occurs
     */
    @Override
    public List<CsvDataEntity> get(Context context) throws Exception {
        assert decoder != null;
        List<CsvDataEntity> data = decoder.decode(context.source);
        if (context.query != null) {
            data = data.stream()
                    .filter(article -> article.title.contains(context.query) || article.body.contains(context.query))
                    .collect(Collectors.toList());
        }
        if (context.fromDate != null) {
            data = data.stream()
                    .filter(article -> article.date.after(context.fromDate))
                    .collect(Collectors.toList());
        }
        if (context.toDate != null) {
            data = data.stream()
                    .filter(article -> article.date.before(context.toDate))
                    .collect(Collectors.toList());
        }
        return data.subList(0, context.countArticles < data.size() ? context.countArticles : data.size());
    }
}