package it.unipd.dei.eis.data.codecs;

import com.google.gson.Gson;

/**
 * JsonEncoder is the encoder for JSON format.
 */
public class JsonEncoder implements IEncoder<Object> {

    /**
     * The Gson object.
     */
    private static final Gson GSON = new Gson();

    /**
     * The encode method is used to encode objects in JSON format.
     *
     * @param object the objects to encode
     * @param args   the arguments
     * @return the JSON string
     */
    @Override
    public String encode(Object object, Object... args) {
        return GSON.toJson(object);
    }
}
