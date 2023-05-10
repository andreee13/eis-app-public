package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.repositories.Repository;
import it.unipd.dei.eis.domain.repositories.TheGuardianRepository;
import it.unipd.dei.eis.presentation.Argument;

import java.util.List;

// priority queue

public class TermsExtractor extends UseCase {

    public TermsExtractor(List<Argument> arguments) {
        super(arguments);
        repository = resolveRepository();
    }

    private Repository<? extends DataSource> resolveRepository() {
        return null;
    }

    @Override
    public void execute() {

    }
}
