package it.unipd.dei.eis.data.serialization;

import com.google.gson.Gson;

/**
 * JsonEncoder is the encoder for JSON format.
 * It uses the Gson library to encode objects in JSON format.
 */
public class JsonEncoder implements IEncoder {

    /**
     * The Gson object.
     */
    private static final Gson gson = new Gson();

    /**
     * The encode method is used to encode objects in JSON format.
     * @param object the object to encode
     * @param args the arguments
     * @return the JSON string
     */
    @Override
    public <T> String encode(T object, Object... args) {
        return gson.toJson(object);
    }
}
