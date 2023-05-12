package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.data.entities.CsvDataEntity;
import it.unipd.dei.eis.data.serialization.CsvDecoder;
import org.apache.commons.cli.CommandLine;

import java.util.List;

public class CsvDataSource extends DataSource {
    private static final String ID = "CSV";

    public CsvDataSource() {
        super(ID, null);
    }

    @Override
    public List<? extends CsvDataEntity> get(CommandLine cmd) throws Exception {
        List<CsvDataEntity> data = new CsvDecoder().decode(cmd.getOptionValue("source"));
        if (cmd.hasOption("query")) {
            data = data.stream().filter(article -> article.title.contains(cmd.getOptionValue("query")) || article.body.contains(cmd.getOptionValue("query"))).collect(java.util.stream.Collectors.toList());
        }
        if (cmd.hasOption("from")) {
            data = data.stream().filter(article -> article.date.after(DateParser.parse(cmd.getOptionValue("from")))).collect(java.util.stream.Collectors.toList());
        }
        if (cmd.hasOption("to")) {
            data = data.stream().filter(article -> article.date.before(DateParser.parse(cmd.getOptionValue("to")))).collect(java.util.stream.Collectors.toList());
        }
        return data.subList(0, cmd.hasOption("count") ? Integer.parseInt(cmd.getOptionValue("count")) : Integer.MAX_VALUE);
    }
}