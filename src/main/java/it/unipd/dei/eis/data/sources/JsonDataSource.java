package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.core.constants.CommandConstants;
import it.unipd.dei.eis.data.entities.JsonDataEntity;
import it.unipd.dei.eis.data.serialization.JsonDecoder;
import it.unipd.dei.eis.data.serialization.JsonEncoder;
import it.unipd.dei.eis.presentation.Context;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class JsonDataSource extends DataSource<JsonDataEntity> {
    private static final String ID = "JSON";

    public JsonDataSource() {
        super(ID, new JsonDecoder(), new JsonEncoder());
    }

    @Override
    public List<JsonDataEntity> get(Context context) throws Exception {
        assert decoder != null;
        return Arrays.asList(decoder.decode(new String(Files.readAllBytes(Paths.get(context.command.equals(CommandConstants.BOTH) ? "output.json" : context.source))), JsonDataEntity[].class));
    }

    @Override
    public void set(Context context, List<JsonDataEntity> data) throws Exception {
        assert encoder != null;
        try (FileWriter fileWriter = new FileWriter(context.output != null ? context.output : "output.json")) {
            fileWriter.write(encoder.encode(data));
        }
    }
}
