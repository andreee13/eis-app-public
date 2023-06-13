package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.data.codecs.JsonDecoder;
import it.unipd.dei.eis.data.codecs.JsonEncoder;
import it.unipd.dei.eis.data.entities.JsonDataEntity;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * JsonDataSource is the data source for JSON records.
 * It contains the data structure of the JSON response.
 */
public class JsonDataSource extends DataSource<JsonDataEntity, Object> {

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
     * Returns the list of entities from the JSON data.
     *
     * @param context the context of the request
     * @return the list of articles
     * @throws Exception if an error occurs
     */
    @Override
    public List<JsonDataEntity> getData(Context context) throws Exception {
        return Arrays.asList((JsonDataEntity[]) Objects.requireNonNull(decoder)
                .decode(Files.readString(Paths.get(context.source)), JsonDataEntity[].class));
    }

    /**
     * Sets the data to the JSON file.
     *
     * @param context the context of the request
     * @param data    the list of articles
     */
    @Override
    public void setData(Context context, List<JsonDataEntity> data) throws Exception {
        Files.writeString(
                Paths.get(context.outputArticles),
                Objects.requireNonNull(encoder).encode(data.toArray(), JsonDataEntity[].class)
        );
    }
}
