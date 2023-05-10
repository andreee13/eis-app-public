package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.errors.Failure;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.data.serialization.JsonEncoder;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.domain.repositories.Repository;
import it.unipd.dei.eis.domain.repositories.TheGuardianRepository;
import it.unipd.dei.eis.presentation.Argument;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArticlesDownloader extends UseCase {

    public ArticlesDownloader(List<Argument> arguments) {
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
