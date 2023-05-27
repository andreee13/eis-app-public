package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.data.entities.IDataEntity;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;

/**
 * A repository for models from a data source.
 *
 * @param <T> The type of the data source
 * @param <M> The type of the model
 */
public abstract class Repository<T extends DataSource<? extends IDataEntity>, M extends IModel> {

    /**
     * The data source.
     */
    protected final T dataSource;

    /**
     * Creates a new Repository.
     *
     * @param dataSource The data source
     */
    Repository(T dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Pulls the articles from the data source.
     *
     * @param context The context to use
     * @return Either a Failure or a List of Articles
     */
    public Either<Failure, List<M>> pull(Context context) {
        throw new UnsupportedOperationException();
    }

    /**
     * Pushes the articles to the data source.
     *
     * @param context The context to use
     * @param models  The articles to push
     * @return Either a Failure or a Success
     */
    public Either<Failure, Success> push(Context context, List<M> models) {
        throw new UnsupportedOperationException();
    }
}
