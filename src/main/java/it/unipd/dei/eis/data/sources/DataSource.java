package it.unipd.dei.eis.data.sources;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.data.codecs.IDecoder;
import it.unipd.dei.eis.data.codecs.IEncoder;
import it.unipd.dei.eis.data.entities.DataEntity;

import java.util.List;

/**
 * DataSource is the abstract class for data sources.
 *
 * @param <E> the type of the data entity
 * @param <D> the type of the codecs data
 */
public abstract class DataSource<E extends DataEntity, D> {

    /**
     * The ID of the data source.
     */
    public final String id;

    /**
     * The decoder of the data source.
     */
    final IDecoder<D> decoder;

    /**
     * The encoder of the data source.
     */
    final IEncoder<D> encoder;

    /**
     * DataSource constructor.
     *
     * @param id      the ID of the data source
     * @param decoder the decoder of the data source
     * @param encoder the encoder of the data source
     */
    DataSource(String id, IDecoder<D> decoder, IEncoder<D> encoder) {
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
    DataSource(String id, IDecoder<D> decoder) {
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
    DataSource(String id, IEncoder<D> encoder) {
        this.id = id;
        this.decoder = null;
        this.encoder = encoder;
    }

    /**
     * Returns the list of data entities from the data source.
     * Data entities are filtered by {@link #filter(Context, List)}.
     *
     * @param context the context of the request
     * @return the list of data entities
     * @throws UnsupportedOperationException if not implemented
     * @throws Exception                     if an error occurs
     */
    final public List<E> get(Context context) throws Exception {
        return filter(context, getData(context));
    }

    /**
     * Returns the list of data entities.
     * Override this method to implement the get operation.
     *
     * @param context the context of the request
     * @return the list of data entities
     * @throws UnsupportedOperationException if not implemented
     * @throws Exception                     if an error occurs
     */
    List<E> getData(Context context) throws Exception {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    /**
     * Sets the list of data entities.
     *
     * @param context the context of the request
     * @param data    the list of data entities
     * @throws UnsupportedOperationException if not implemented
     * @throws Exception                     if an error occurs
     */
    final public void set(Context context, List<E> data) throws Exception {
        setData(context, data);
    }

    /**
     * Sets the list of data entities.
     * Override this method to implement the set operation.
     *
     * @param context the context of the request
     * @param data    the list of data entities
     * @throws UnsupportedOperationException if not implemented
     * @throws Exception                     if an error occurs
     */
    void setData(Context context, List<E> data) throws Exception {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    /**
     * Filters the list of data entities by query, from date and to date, if supported.
     * Internally used by {@link #get(Context)}.
     *
     * @param context the context of the request
     * @param data    the list of data entities
     * @return the filtered list of data entities
     */
    private List<E> filter(Context context, List<E> data) {
        if (context.query != null) {
            try {
                data = data.stream()
                        .filter(entity -> entity.contains(context.query))
                        .collect(java.util.stream.Collectors.toList());
            } catch (UnsupportedOperationException ignored) {
            }
        }
        if (context.fromDate != null) {
            try {
                data = data.stream()
                        .filter(entity -> entity.after(context.fromDate))
                        .collect(java.util.stream.Collectors.toList());
            } catch (UnsupportedOperationException ignored) {
            }
        }
        if (context.toDate != null) {
            try {
                data = data.stream()
                        .filter(entity -> entity.before(context.toDate))
                        .collect(java.util.stream.Collectors.toList());
            } catch (UnsupportedOperationException ignored) {
            }
        }
        return data.subList(0, Math.min(context.countArticles, data.size()));
    }
}
