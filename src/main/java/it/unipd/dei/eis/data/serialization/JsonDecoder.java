package it.unipd.dei.eis.data.serialization;

import com.google.gson.Gson;

public class JsonDecoder implements IDecoder {
    private static final Gson gson = new Gson();

    @Override
    @SuppressWarnings("unchecked")
    public <T> T decode(String data, Object... args) {
        return gson.fromJson(data, (Class<T>) args[0]);
    }
}