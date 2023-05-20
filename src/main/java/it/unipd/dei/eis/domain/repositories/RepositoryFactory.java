package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.data.entities.IDataEntity;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;

public class RepositoryFactory {
    public static Repository<? extends DataSource<? extends IDataEntity>, ? extends IModel> create(String option) throws IllegalArgumentException {
        if (option.equals("extract")) {
            return new TermsExtractionRepository();
        } else if (option.equals("theguardian")) {
            return new TheGuardianRepository();
        } else if (option.endsWith(".csv")) {
            return new CsvRepository();
        } else if (option.endsWith(".json")) {
            return new JsonRepository();
        } else {
            throw new IllegalArgumentException("Invalid repository");
        }
    }
}
