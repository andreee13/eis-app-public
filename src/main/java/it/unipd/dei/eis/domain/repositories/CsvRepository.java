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
public class CsvRepository extends Repository<CsvDataSource, Article> {

    /**
     * Creates a new CsvRepository.
     */
    CsvRepository() {
        super(new CsvDataSource());
    }

    /**
     * Converts a CsvDataEntity to an Article.
     *
     * @param result The CsvDataEntity to convert
     * @return The Article
     */
    private Article resultToArticle(CsvDataEntity result) {
        return new Article(
                result.identifier,
                result.title,
                result.body,
                result.url,
                result.date,
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
                    .map(this::resultToArticle)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return Either.failure(new Failure(e, "Failed to get data from data source"));
        }
    }
}
