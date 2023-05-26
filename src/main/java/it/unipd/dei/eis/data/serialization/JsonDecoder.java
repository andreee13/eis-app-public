package it.unipd.dei.eis.data.serialization;

import com.google.gson.Gson;

/**
 * JsonDecoder is the decoder for JSON data.
 */
public class JsonDecoder implements IDecoder {

    /**
     * The Gson object is used to parse the JSON response.
     */
    private static final Gson gson = new Gson();

    /**
     * The decode method is used to decode the JSON response.
     *
     * @param data the JSON response
     * @param args the arguments
     * @return the data entity
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T decode(String data, Object... args) {
        return gson.fromJson(data, (Class<T>) args[0]);
    }
}