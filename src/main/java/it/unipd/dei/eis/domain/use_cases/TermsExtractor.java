package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.repositories.Repository;
import org.apache.commons.cli.CommandLine;

// priority queue

public class TermsExtractor extends UseCase {

    public TermsExtractor(CommandLine cmd) {
        super(cmd);
        repository = resolveRepository();
    }

    private Repository<? extends DataSource> resolveRepository() {
        return null;
    }

    @Override
    public void execute() {

    }
}
