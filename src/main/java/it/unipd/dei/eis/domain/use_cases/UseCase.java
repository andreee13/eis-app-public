package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.repositories.Repository;
import org.apache.commons.cli.CommandLine;

public abstract class UseCase {
    Repository repository;
    final CommandLine cmd;

    public UseCase(CommandLine cmd) {
        this.cmd = cmd;
    }

    public abstract void execute();
}
