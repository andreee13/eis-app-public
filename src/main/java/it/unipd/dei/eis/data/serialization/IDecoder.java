package it.unipd.dei.eis.data.serialization;

public interface IDecoder {
    <T> T decode(String data, Object... args) throws Exception;
}
