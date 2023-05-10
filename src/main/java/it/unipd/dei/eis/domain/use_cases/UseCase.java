package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.repositories.Repository;
import it.unipd.dei.eis.presentation.Argument;

import java.util.List;

public abstract class UseCase {
    List<Argument> arguments;
    Repository repository;

    public UseCase(List<Argument> arguments) {
        this.arguments = arguments;
    }

    public abstract void execute();
}
