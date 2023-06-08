package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.data.entities.DataEntity;
import it.unipd.dei.eis.data.sources.DataSource;
import it.unipd.dei.eis.domain.models.IModel;

/**
 * A factory for repositories.
 */
public abstract class RepositoryFactory {

    /**
     * Creates a new repository.
     *
     * @param option The option to use
     * @param <S>    The data source
     * @param <E>    The data entity
     * @param <M>    The model
     * @return The repository
     * @throws IllegalArgumentException If the option is invalid
     */
    @SuppressWarnings("unchecked")
    public static <S extends DataSource<E, ?>, E extends DataEntity, M extends IModel> Repository<S, E, M> create(String option) throws IllegalArgumentException {
        final Repository<? extends DataSource<? extends DataEntity, ?>, ? extends DataEntity, ? extends IModel> repository;
        if (option.equals("theguardian")) {
            repository = TheGuardianRepository.getInstance();
        } else if (option.endsWith(".txt")) {
            repository = TermsExtractionRepository.getInstance();
        } else if (option.endsWith(".csv")) {
            repository = CsvRepository.getInstance();
        } else if (option.endsWith(".json")) {
            repository = JsonRepository.getInstance();
        } else {
            throw new IllegalArgumentException(String.format("\"%s\" is not a valid option", option));
        }
        return (Repository<S, E, M>) repository;
    }
}
