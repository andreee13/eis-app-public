package it.unipd.dei.eis.data.serialization;

import com.google.gson.Gson;

public class JsonEncoder implements IEncoder {
    private static final Gson gson = new Gson();

    @Override
    public <T> String encode(T object, Object... args) {
        return gson.toJson(object);
    }
}
