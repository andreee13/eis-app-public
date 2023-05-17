package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.data.entities.JsonDataEntity;
import it.unipd.dei.eis.data.serialization.JsonDecoder;
import it.unipd.dei.eis.presentation.Context;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class JsonDataSource extends DataSource {
    private static final String ID = "JSON";

    public JsonDataSource() {
        super(ID, new JsonDecoder());
    }

    @Override
    public List<JsonDataEntity> get(Context context) throws Exception {
        return Arrays.asList(decoder.decode(new String(Files.readAllBytes(Paths.get(context.source))), JsonDataEntity[].class));
    }
}
