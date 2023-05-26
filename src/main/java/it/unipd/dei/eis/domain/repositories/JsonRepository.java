package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.data.entities.JsonDataEntity;
import it.unipd.dei.eis.data.sources.JsonDataSource;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A repository for articles from a JSON data source.
 */
public class JsonRepository extends Repository<JsonDataSource, Article> {

    /**
     * Creates a new Repository.
     */
    JsonRepository() {
        super(new JsonDataSource());
    }

    /**
     * Converts a JsonDataEntity to an Article.
     * @param result The JsonDataEntity to convert
     * @return The Article
     */
    private Article resultToArticle(JsonDataEntity result) {
        return new Article(
                result.id,
                result.title,
                result.body,
                result.url,
                result.date,
                dataSource.id
        );
    }

    /**
     * Converts an Article to a JsonDataEntity.
     * @param article The article to convert
     * @return The JsonDataEntity
     */
    private JsonDataEntity articleToResult(Article article) {
        return new JsonDataEntity(
                article.id,
                article.title,
                article.body,
                article.url,
                article.date,
                article.source
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
                    .stream()
                    .map(this::resultToArticle)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return Either.failure(new Failure(e, "Failed to get data from data source"));
        }
    }

    /**
     * Pushes the given articles to the data source.
     * @param context The context to use
     * @param articles The articles to push
     * @return Either a Failure or a Success
     */
    @Override
    public Either<Failure, Success> push(Context context, List<Article> articles) {
        try {
            dataSource.set(context, articles.stream()
                    .map(this::articleToResult)
                    .collect(Collectors.toList()));
            return Either.success(new Success());
        } catch (Exception e) {
            return Either.failure(new Failure(e, "Failed to set data to data source"));
        }
    }
}
