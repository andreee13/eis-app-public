package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.errors.Failure;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;
import org.apache.commons.cli.CommandLine;

import java.util.List;

public abstract class Repository<T extends DataSource> {
    final T dataSource;

    protected Repository(T dataSource) {
        this.dataSource = dataSource;
    }

    public abstract <M extends IModel> Either<Failure, ? extends List<M>> fetch(CommandLine cmd);
}
