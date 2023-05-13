package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Factory;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;
import org.apache.commons.cli.CommandLine;

public class RepositoryFactory extends Factory<Repository<? extends DataSource, ? extends IModel>> {
    public RepositoryFactory(CommandLine cmd) {
        super(cmd);
    }

    @Override
    public Repository<? extends DataSource, ? extends IModel> create(String name) throws IllegalArgumentException {
        if (name.equals("theguardian")) {
            return new TheGuardianRepository(cmd);
        } else if (name.endsWith(".csv")) {
            return new CsvRepository(cmd);
        } else {
            throw new IllegalArgumentException("Invalid repository");
        }
    }
}
