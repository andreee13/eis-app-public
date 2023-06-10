package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.data.entities.JsonDataEntity;
import it.unipd.dei.eis.data.sources.JsonDataSource;
import it.unipd.dei.eis.domain.models.ArticleModel;

import java.util.List;

/**
 * A repository for articles from a JSON data source.
 */
public class JsonRepository extends Repository<JsonDataSource, JsonDataEntity, ArticleModel> {

    /**
     * The instance of the JsonRepository class.
     */
    private static JsonRepository instance;

    /**
     * Creates a new Repository.
     */
    private JsonRepository() {
        super(new JsonDataSource());
    }

    /**
     * Returns the instance of the JsonRepository class.
     *
     * @return The instance of the JsonRepository class
     */
    public static synchronized JsonRepository getInstance() {
        if (instance == null) {
            instance = new JsonRepository();
        }
        return instance;
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
}
