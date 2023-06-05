package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.data.entities.DataEntity;
import it.unipd.dei.eis.data.codecs.IDecoder;
import it.unipd.dei.eis.data.codecs.IEncoder;

import java.util.List;

/**
 * DataSource is the abstract class for data sources.
 *
 * @param <E> the type of the data entity
 */
public abstract class DataSource<E extends DataEntity> {

    /**
     * The ID of the data source.
     */
    public final String id;

    /**
     * The decoder of the data source.
     */
    final IDecoder decoder;

    /**
     * The encoder of the data source.
     */
    final IEncoder encoder;

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
     * Returns the list of data entities.
     *
     * @param context the context of the request
     * @return the list of data entities
     * @throws UnsupportedOperationException if not implemented
     * @throws Exception                     if an error occurs
     */
    public List<E> get(Context context) throws Exception {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the list of data entities.
     *
     * @param context the context of the request
     * @param data    the list of data entities
     * @throws UnsupportedOperationException if not implemented
     * @throws Exception                     if an error occurs
     */
    public void set(Context context, List<E> data) throws Exception {
        throw new UnsupportedOperationException();
    }

    /**
     * Filters the list of data entities.
     * Internally used by {@link #get(Context)}.
     *
     * @param context the context of the request
     * @param data    the list of data entities
     * @return the filtered list of data entities
     */
    List<E> filter(Context context, List<E> data) {
        if (context.query != null) {
            data = data.stream()
                    .filter(entity -> entity.contains(context.query))
                    .collect(java.util.stream.Collectors.toList());
        }
        if (context.fromDate != null) {
            data = data.stream()
                    .filter(entity -> entity.after(context.fromDate))
                    .collect(java.util.stream.Collectors.toList());
        }
        if (context.toDate != null) {
            data = data.stream()
                    .filter(entity -> entity.before(context.toDate))
                    .collect(java.util.stream.Collectors.toList());
        }
        return data.subList(0, Math.min(context.countArticles, data.size()));
    }
}