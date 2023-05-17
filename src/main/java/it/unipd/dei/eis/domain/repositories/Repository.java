package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.errors.Failure;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;

import java.util.List;

public abstract class Repository<T extends DataSource, M extends IModel> {
    protected final T dataSource;

    protected Repository(T dataSource) {
        this.dataSource = dataSource;
    }

    public abstract Either<Failure, List<M>> fetch(Context context);
}
