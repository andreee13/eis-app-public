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
 * Repository is the abstract class for repositories.
 *
 * @param <S> The data source
 * @param <E> The data entity
 * @param <M> The model
 */
public abstract class Repository<S extends DataSource<E>, E extends IDataEntity, M extends IModel> {

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
     * Pulls the articles from the data source.
     *
     * @param context The context to use
     * @return Either a Failure or a List of Articles
     * @throws UnsupportedOperationException if not implemented
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
     * @throws UnsupportedOperationException if not implemented
     */
    public Either<Failure, Success> push(Context context, List<M> models) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adapts a data entity to a model.
     *
     * @param dataEntity The data entity
     * @return The model
     * @throws UnsupportedOperationException if not implemented
     */
    M adapt(E dataEntity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adapts a model to a data entity.
     *
     * @param model The model
     * @return The data entity
     * @throws UnsupportedOperationException if not implemented
     */
    E adapt(M model) {
        throw new UnsupportedOperationException();
    }
}
