package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.data.entities.IDataEntity;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;

/**
 * A factory for repositories.
 */
public interface RepositoryFactory {

    /**
     * Creates a new repository.
     *
     * @param option The option (command) to use
     * @return The repository
     * @throws IllegalArgumentException If the option (command) is invalid
     */
    @SuppressWarnings("unchecked")
    static <S extends DataSource<E>, E extends IDataEntity, M extends IModel> Repository<S, E, M> create(String option) throws IllegalArgumentException {
        final Repository<? extends DataSource<? extends IDataEntity>, ? extends IDataEntity, ? extends IModel> repository;
        if (option.equals("extract")) {
            repository = new TermsExtractionRepository();
        } else if (option.equals("theguardian")) {
            repository = new TheGuardianRepository();
        } else if (option.endsWith(".csv")) {
            repository = new CsvRepository();
        } else if (option.endsWith(".json")) {
            repository = new JsonRepository();
        } else {
            throw new IllegalArgumentException("Invalid repository");
        }
        return (Repository<S, E, M>) repository;
    }
}
