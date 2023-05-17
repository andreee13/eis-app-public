package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;

public class RepositoryFactory {
    public static Repository<? extends DataSource, ? extends IModel> create(String source) throws IllegalArgumentException {
        if (source.equals("theguardian")) {
            return new TheGuardianRepository();
        } else if (source.endsWith(".csv")) {
            return new CsvRepository();
        } else if (source.endsWith(".json")) {
            return new JsonRepository();
        } else {
            throw new IllegalArgumentException("Invalid repository");
        }
    }
}
