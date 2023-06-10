package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.data.entities.TermsDataEntity;
import it.unipd.dei.eis.data.sources.TermsDataSource;
import it.unipd.dei.eis.domain.models.ArticleModel;

import java.util.List;

/**
 * A repository for models from a data source.
 */
public class TermsExtractionRepository extends Repository<TermsDataSource, TermsDataEntity, ArticleModel> {

    /**
     * The instance of the TermsExtractionRepository class.
     */
    private static TermsExtractionRepository instance;

    /**
     * Creates a new Repository.
     */
    private TermsExtractionRepository() {
        super(new TermsDataSource());
    }

    /**
     * Returns the instance of the TermsExtractionRepository class.
     *
     * @return The instance of the TermsExtractionRepository class
     */
    public static synchronized TermsExtractionRepository getInstance() {
        if (instance == null) {
            instance = new TermsExtractionRepository();
        }
        return instance;
    }

    /**
     * Adapts a model to a data entity.
     * Internally used by {@link #push(Context, List)}.
     *
     * @param model The model
     * @return The data entity
     */
    @Override
    TermsDataEntity adapt(ArticleModel model) {
        return new TermsDataEntity(
                model.title,
                model.body
        );
    }

    /**
     * Unsupported operation.
     *
     * @param context The context to use
     * @return List of models
     */
    @Override
    List<ArticleModel> pullData(Context context) {
        throw new UnsupportedOperationException("Operation not supported");
    }
}
