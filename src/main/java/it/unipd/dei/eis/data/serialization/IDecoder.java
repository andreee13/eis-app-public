package it.unipd.dei.eis.data.serialization;

/**
 * IDecoder is the interface for the decoder.
 */
public interface IDecoder {

    /**
     * Decodes the data.
     *
     * @param data the data to decode
     * @param args optional arguments
     * @param <T>  the type of the data
     * @return the decoded data
     * @throws Exception if an error occurs
     */
    <T> T decode(String data, Object... args) throws Exception;
}
