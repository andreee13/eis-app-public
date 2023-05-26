package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.data.entities.TheGuardianIDataEntity;
import it.unipd.dei.eis.data.sources.TheGuardianDataSource;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A repository for models from a data source.
 * @param <T> The type of the data source
 * @param <M> The type of the model
 */
public class TheGuardianRepository extends Repository<TheGuardianDataSource, Article> {

    /**
     * Creates a new Repository.
     */
    TheGuardianRepository() {
        super(new TheGuardianDataSource());
    }

    /**
     * Converts a data entity to an article.
     * @param result The data entity to convert
     * @return The article
     */
    private Article resultToArticle(TheGuardianIDataEntity.Response.Result result) {
        return new Article(
                result.id,
                result.webTitle,
                result.fields.bodyText,
                result.webUrl,
                result.webPublicationDate,
                dataSource.id
        );
    }

    /**
     * Pulls the articles from the data source.
     * @param context The context to use
     * @return Either a Failure or a List of Articles
     */
    @Override
    public Either<Failure, List<Article>> pull(Context context) {
        try {
            return Either.success(dataSource.get(context)
                    .get(0).response.results.stream()
                    .map(this::resultToArticle)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return Either.failure(new Failure(e, "Failed to get data from data source"));
        }
    }
}
