package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;
import it.unipd.dei.eis.domain.repositories.Repository;
import org.apache.commons.cli.CommandLine;

public abstract class UseCase {
    final CommandLine cmd;
    Repository<? extends DataSource, ? extends IModel> repository;

    public UseCase(CommandLine cmd) {
        this.cmd = cmd;
    }

    public abstract void execute();
}
