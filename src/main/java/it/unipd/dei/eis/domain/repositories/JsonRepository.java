package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.data.entities.JsonDataEntity;
import it.unipd.dei.eis.data.sources.JsonDataSource;
import it.unipd.dei.eis.domain.models.ArticleModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A repository for articles from a JSON data source.
 */
public class JsonRepository extends Repository<JsonDataSource, JsonDataEntity, ArticleModel> {

    /**
     * Creates a new Repository.
     */
    JsonRepository() {
        super(new JsonDataSource());
    }

    /**
     * Adapts a data entity to a model.
     * Internally used by {@link #pull(Context)}.
     *
     * @param dataEntity The data entity
     * @return The model
     */
    @Override
    ArticleModel adapt(JsonDataEntity dataEntity) {
        return new ArticleModel(
                dataEntity.title,
                dataEntity.body,
                dataEntity.url,
                dataEntity.date,
                dataEntity.source
        );
    }

    /**
     * Adapts a model to a data entity.
     * Internally used by {@link #push(Context, List)}.
     *
     * @param model The model
     * @return The data entity
     */
    @Override
    JsonDataEntity adapt(ArticleModel model) {
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
     * @return List of articles
     */
    @Override
    public List<ArticleModel> pull(Context context) throws Exception {
        return dataSource.get(context)
                .stream()
                .map(this::adapt)
                .collect(Collectors.toList());
    }

    /**
     * Pushes the given articleModels to the data source.
     *
     * @param context       The context to use
     * @param articleModels The articleModels to push
     */
    @Override
    public void push(Context context, List<ArticleModel> articleModels) throws Exception {
        dataSource.set(context, articleModels.stream()
                .map(this::adapt)
                .collect(Collectors.toList()));
    }
}
