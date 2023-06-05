package it.unipd.dei.eis.data.codecs;

/**
 * IEncoder is the interface for the encoder.
 */
public interface IEncoder {

    /**
     * Encodes the object.
     *
     * @param object the object to encode
     * @param args   optional arguments
     * @param <T>    the type of the object
     * @return the encoded object
     * @throws Exception if an error occurs
     */
    <T> String encode(T object, Object... args) throws Exception;
}
