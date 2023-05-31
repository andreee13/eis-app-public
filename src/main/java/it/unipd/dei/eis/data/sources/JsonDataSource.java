package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.core.constants.DefaultSettings;
import it.unipd.dei.eis.data.entities.JsonDataEntity;
import it.unipd.dei.eis.data.serialization.JsonDecoder;
import it.unipd.dei.eis.data.serialization.JsonEncoder;
import it.unipd.dei.eis.presentation.Context;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * JsonDataSource is the data source for JSON records.
 * It contains the data structure of the JSON response.
 */
public class JsonDataSource extends DataSource<JsonDataEntity> {

    /**
     * The ID of the data source.
     */
    public static final String ID = "JSON";

    /**
     * JsonDataSource constructor.
     */
    public JsonDataSource() {
        super(ID, new JsonDecoder(), new JsonEncoder());
    }

    /**
     * Returns the list of articles.
     *
     * @param context the context of the request
     * @return the list of articles
     * @throws Exception if an error occurs
     */
    @Override
    public List<JsonDataEntity> get(Context context) throws Exception {
        return Arrays.asList(Objects.requireNonNull(decoder)
                .decode(new String(Files.readAllBytes(Paths.get(context.output != null ? String.format("%s.json", context.output) : DefaultSettings.JSON_FILE_NAME))), JsonDataEntity[].class));
    }

    /**
     * Sets the list of articles.
     *
     * @param context the context of the request
     * @param data    the list of articles
     * @throws Exception if an error occurs
     */
    @Override
    public void set(Context context, List<JsonDataEntity> data) throws Exception {
        try (FileWriter fileWriter = new FileWriter(context.output != null ? String.format("%s.json", context.output) : DefaultSettings.JSON_FILE_NAME)) {
            fileWriter.write(Objects.requireNonNull(encoder)
                    .encode(data));
        }
    }
}
