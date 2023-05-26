package it.unipd.dei.eis.data.serialization;

/**
 * IEncoder is the interface for the encoder.
 */
public interface IEncoder {
    <T> String encode(T object, Object... args);
}
