package it.unipd.dei.eis.data.codecs;

/**
 * IEncoder is the interface for the encoder.
 *
 * @param <D> the type of object to encode
 */
public interface IEncoder<D> {

    /**
     * Encodes the object.
     *
     * @param object the object to encode
     * @param args    optional arguments
     * @return the encoded object
     * @throws Exception if an error occurs
     */
    String encode(D object, Object... args) throws Exception;
}
