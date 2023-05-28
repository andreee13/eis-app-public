package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.data.entities.CsvDataEntity;
import it.unipd.dei.eis.data.sources.CsvDataSource;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A repository for articles from a CSV data source.
 */
public class CsvRepository extends Repository<CsvDataSource, CsvDataEntity, Article> {

    /**
     * Creates a new CsvRepository.
     */
    CsvRepository() {
        super(new CsvDataSource());
    }

    /**
     * Adapts a data entity to a model.
     *
     * @param dataEntity The data entity
     * @return The model
     */
    @Override
    Article adapt(CsvDataEntity dataEntity) {
        return new Article(
                dataEntity.identifier,
                dataEntity.title,
                dataEntity.body,
                dataEntity.url,
                dataEntity.date,
                dataSource.id
        );
    }

    /**
     * Pulls the articles from the data source.
     *
     * @param context The context to use
     * @return Either a Failure or a List of Articles
     */
    @Override
    public Either<Failure, List<Article>> pull(Context context) {
        try {
            return Either.success(dataSource.get(context)
                    .stream()
                    .map(this::adapt)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return Either.failure(new Failure(e, "Failed to get data from data source"));
        }
    }
}
