package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.data.entities.CsvDataEntity;
import it.unipd.dei.eis.data.serialization.CsvDecoder;

import java.util.List;
import java.util.stream.Collectors;

public class CsvDataSource extends DataSource {
    private static final String ID = "CSV";

    public CsvDataSource() {
        super(ID, null);
    }

    @Override
    public List<? extends CsvDataEntity> get(Context context) throws Exception {
        List<CsvDataEntity> data = new CsvDecoder().decode(context.source);
        if (context.query != null) {
            data = data.stream().filter(article -> article.title.contains(context.query) || article.body.contains(context.query)).collect(Collectors.toList());
        }
        if (context.fromDate != null) {
            data = data.stream().filter(article -> article.date.after(context.fromDate)).collect(java.util.stream.Collectors.toList());
        }
        if (context.toDate != null) {
            data = data.stream().filter(article -> article.date.before(context.toDate)).collect(java.util.stream.Collectors.toList());
        }
        return data.subList(0, context.count != null ? context.count : Integer.MAX_VALUE);
    }
}