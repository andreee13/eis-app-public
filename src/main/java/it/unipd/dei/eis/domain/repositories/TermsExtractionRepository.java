package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.data.entities.ArticleTermsDataEntity;
import it.unipd.dei.eis.data.sources.TermsDataSource;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A repository for models from a data source.
 */
public class TermsExtractionRepository extends Repository<TermsDataSource, Article> {

    /**
     * Creates a new Repository.
     */
    TermsExtractionRepository() {
        super(new TermsDataSource());
    }

    /**
     * Converts an Article to an ArticleTermsDataEntity.
     *
     * @param article The Article to convert
     * @return The ArticleTermsDataEntity
     */
    private ArticleTermsDataEntity articleToResult(Article article) {
        return new ArticleTermsDataEntity(
                article.title,
                article.body
        );
    }

    /**
     * Pushes the articles to the data source.
     *
     * @param context The context to use
     * @param models  The articles to push
     * @return Either a Failure or a Success
     */
    @Override
    public Either<Failure, Success> push(Context context, List<Article> models) {
        try {
            dataSource.set(context, models.stream()
                    .map(this::articleToResult)
                    .collect(Collectors.toList()));
            return Either.success(new Success());
        } catch (Exception e) {
            return Either.failure(new Failure(e, "Failed to set data to data source"));
        }
    }
}
