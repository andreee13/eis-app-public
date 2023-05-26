package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.data.entities.IDataEntity;
import it.unipd.dei.eis.data.serialization.IDecoder;
import it.unipd.dei.eis.data.serialization.IEncoder;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;

/**
 * DataSource is the abstract class for data sources.
 */
public abstract class DataSource<T extends IDataEntity> {

    /**
     * The ID of the data source.
     */
    public final String id;

    /**
     * The decoder of the data source.
     */
    protected final IDecoder decoder;

    /**
     * The encoder of the data source.
     */
    protected final IEncoder encoder;

    /**
     * DataSource constructor.
     *
     * @param id      the ID of the data source
     * @param decoder the decoder of the data source
     * @param encoder the encoder of the data source
     */
    DataSource(String id, IDecoder decoder, IEncoder encoder) {
        this.id = id;
        this.decoder = decoder;
        this.encoder = encoder;
    }

    /**
     * DataSource constructor.
     *
     * @param id      the ID of the data source
     * @param decoder the decoder of the data source
     */
    DataSource(String id, IDecoder decoder) {
        this.id = id;
        this.decoder = decoder;
        this.encoder = null;
    }

    /**
     * DataSource constructor.
     *
     * @param id      the ID of the data source
     * @param encoder the encoder of the data source
     */
    DataSource(String id, IEncoder encoder) {
        this.id = id;
        this.decoder = null;
        this.encoder = encoder;
    }

    /**
     * DataSource constructor.
     *
     * @param id the ID of the data source
     */
    DataSource(String id) {
        this.id = id;
        this.decoder = null;
        this.encoder = null;
    }

    /**
     * Returns the list of data entities.
     *
     * @param context the context of the request
     * @return the list of data entities
     * @throws UnsupportedOperationException if an error occurs
     */
    public List<T> get(Context context) throws Exception {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the list of data entities.
     *
     * @param context the context of the request
     * @param data    the list of data entities
     * @throws UnsupportedOperationException if an error occurs
     */
    public void set(Context context, List<T> data) throws Exception {
        throw new UnsupportedOperationException();
    }
}

