package it.unipd.dei.eis.data.serialization;

/**
 * IDecoder is the interface for the decoder.
 */
public interface IDecoder {
    <T> T decode(String data, Object... args) throws Exception;
}
