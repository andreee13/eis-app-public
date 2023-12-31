package it.unipd.dei.eis.data.codecs;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * JsonDecoder is the decoder for JSON data.
 */
public class JsonDecoder implements IDecoder<Object> {

    /**
     * The Gson object is used to parse the JSON response.
     */
    private static final Gson GSON = new Gson();

    /**
     * The decode method is used to decode the JSON response.
     *
     * @param data the JSON response
     * @param args the arguments
     * @return the data entity
     */
    @Override
    public Object decode(String data, Object... args) {
        return GSON.fromJson(data, (Type) args[0]);
    }
}
