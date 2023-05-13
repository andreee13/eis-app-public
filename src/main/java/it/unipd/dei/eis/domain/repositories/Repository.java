package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.errors.Failure;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;
import org.apache.commons.cli.CommandLine;

import java.util.List;

public abstract class Repository<T extends DataSource, M extends IModel> {
    protected final CommandLine cmd;
    protected final T dataSource;

    protected Repository(CommandLine cmd, T dataSource) {
        this.cmd = cmd;
        this.dataSource = dataSource;
    }

    public abstract Either<Failure, List<M>> fetch(CommandLine cmd);
}
