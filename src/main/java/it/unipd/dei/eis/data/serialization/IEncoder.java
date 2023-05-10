package it.unipd.dei.eis.data.serialization;

public interface IEncoder {
    <T> String encode(T object, Object... args) throws Exception;
}
