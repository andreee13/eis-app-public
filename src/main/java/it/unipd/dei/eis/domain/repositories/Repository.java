package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Cache;
import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.data.entities.DataEntity;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository is the abstract class for repositories.
 *
 * @param <S> The data source
 * @param <E> The data entity
 * @param <M> The model
 */
public abstract class Repository<S extends DataSource<E, ?>, E extends DataEntity, M extends IModel> extends Cache<Context, List<M>> {

    /**
     * The data source.
     */
    final S dataSource;

    /**
     * Creates a new Repository.
     *
     * @param dataSource The data source
     */
    Repository(S dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Pulls the models from the data source.
     * If the models are cached, the cache is used to improve performance.
     *
     * @param context The context to use
     * @return List of models
     * @throws UnsupportedOperationException if not implemented
     * @throws Exception                     if an error occurs
     */
    final public List<M> pull(Context context) throws Exception {
        if (isCached(context)) {
            return getCache(context);
        }
        List<M> models = pullData(context);
        putCache(context, models);
        return models;
    }

    /**
     * Pulls the models from the data source.
     * Provides a default implementation that uses the data source.
     * Override this method to implement a custom pull.
     *
     * @param context The context to use
     * @return List of models
     * @throws Exception if an error occurs
     */
    List<M> pullData(Context context) throws Exception {
        return dataSource.get(context)
                .stream()
                .map(this::adapt)
                .collect(Collectors.toList());
    }

    /**
     * Pushes the models to the data source.
     *
     * @param context The context to use
     * @param models  The models to push
     * @throws UnsupportedOperationException if not implemented
     * @throws Exception                     if an error occurs
     */
    final public void push(Context context, List<M> models) throws Exception {
        pushData(context, models);
    }

    /**
     * Pushes the models to the data source.
     * Provides a default implementation that uses the data source.
     * Override this method to implement a custom push.
     *
     * @param context The context to use
     * @param models  The models to push
     * @throws UnsupportedOperationException if not implemented
     * @throws Exception                     if an error occurs
     */
    void pushData(Context context, List<M> models) throws Exception {
        dataSource.set(context, models.stream()
                .map(this::adapt)
                .collect(Collectors.toList()));
    }

    /**
     * Adapts a data entity to a model.
     * Internally used by {@link #pull(Context)}.
     *
     * @param dataEntity The data entity
     * @return The model
     * @throws UnsupportedOperationException if not implemented
     */
    M adapt(E dataEntity) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    /**
     * Adapts a model to a data entity.
     * Internally used by {@link #push(Context, List)}.
     *
     * @param model The model
     * @return The data entity
     * @throws UnsupportedOperationException if not implemented
     */
    E adapt(M model) {
        throw new UnsupportedOperationException("Unsupported operation");
    }
}
