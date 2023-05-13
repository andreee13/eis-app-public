package it.unipd.dei.eis.domain.use_cases;

import org.apache.commons.cli.CommandLine;

// priority queue

public class TermsExtractor extends UseCase {

    public TermsExtractor(CommandLine cmd) {
        super(cmd);
        repository = null;
    }

    @Override
    public void execute() {

    }
}
