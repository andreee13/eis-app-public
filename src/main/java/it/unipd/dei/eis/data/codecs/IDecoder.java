package it.unipd.dei.eis.data.codecs;

/**
 * IDecoder is the interface for the decoder.
 *
 * @param <D> the type of object to decode
 */
public interface IDecoder<D> {

    /**
     * Decodes the data.
     *
     * @param data the data to decode
     * @param args optional arguments
     * @return the decoded data
     * @throws Exception if an error occurs
     */
    D decode(String data, Object... args) throws Exception;
}
