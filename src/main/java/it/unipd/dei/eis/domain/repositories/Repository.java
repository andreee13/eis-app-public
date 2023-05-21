package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.data.entities.IDataEntity;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;

public abstract class Repository<T extends DataSource<? extends IDataEntity>, M extends IModel> {
    protected final T dataSource;

    protected Repository(T dataSource) {
        this.dataSource = dataSource;
    }

    public Either<Failure, List<M>> pull(Context context) {
        throw new UnsupportedOperationException();
    }

    public Either<Failure, Success> push(Context context, List<M> models) {
        throw new UnsupportedOperationException();
    }
}
