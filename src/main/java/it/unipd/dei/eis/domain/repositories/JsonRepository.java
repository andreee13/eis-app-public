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
public class JsonRepository extends Repository<JsonDataSource, JsonDataEntity, Article> {

    /**
     * Creates a new Repository.
     */
    JsonRepository() {
        super(new JsonDataSource());
    }

    /**
     * Adapts a data entity to a model.
     *
     * @param dataEntity The data entity
     * @return The model
     */
    @Override
    Article adapt(JsonDataEntity dataEntity) {
        return new Article(
                dataEntity.id,
                dataEntity.title,
                dataEntity.body,
                dataEntity.url,
                dataEntity.date,
                dataSource.id
        );
    }

    /**
     * Adapts a model to a data entity.
     *
     * @param model The model
     * @return The data entity
     */
    @Override
    JsonDataEntity adapt(Article model) {
        return new JsonDataEntity(
                model.id,
                model.title,
                model.body,
                model.url,
                model.date,
                model.source
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

    /**
     * Pushes the given articles to the data source.
     *
     * @param context  The context to use
     * @param articles The articles to push
     * @return Either a Failure or a Success
     */
    @Override
    public Either<Failure, Success> push(Context context, List<Article> articles) {
        try {
            dataSource.set(context, articles.stream()
                    .map(this::adapt)
                    .collect(Collectors.toList()));
            return Either.success(new Success());
        } catch (Exception e) {
            return Either.failure(new Failure(e, "Failed to set data to data source"));
        }
    }
}
