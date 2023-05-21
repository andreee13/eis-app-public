package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.data.entities.CsvDataEntity;
import it.unipd.dei.eis.data.serialization.CsvDecoder;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;
import java.util.stream.Collectors;

public class CsvDataSource extends DataSource<CsvDataEntity> {
    private static final String ID = "CSV";

    public CsvDataSource() {
        super(ID, new CsvDecoder());
    }

    @Override
    public List<CsvDataEntity> get(Context context) throws Exception {
        assert decoder != null;
        List<CsvDataEntity> data = decoder.decode(context.source);
        if (context.query != null) {
            data = data.stream().filter(article -> article.title.contains(context.query) || article.body.contains(context.query)).collect(Collectors.toList());
        }
        if (context.fromDate != null) {
            data = data.stream().filter(article -> article.date.after(context.fromDate)).collect(Collectors.toList());
        }
        if (context.toDate != null) {
            data = data.stream().filter(article -> article.date.before(context.toDate)).collect(Collectors.toList());
        }
        return data.subList(0, context.countArticles < data.size() ? context.countArticles : data.size());
    }
}