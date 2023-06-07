package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.data.entities.CsvDataEntity;
import it.unipd.dei.eis.data.sources.CsvDataSource;
import it.unipd.dei.eis.domain.models.ArticleModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A repository for articles from a CSV data source.
 */
public class CsvRepository extends Repository<CsvDataSource, CsvDataEntity, ArticleModel> {

    /**
     * The instance of the CsvRepository class.
     */
    private static CsvRepository instance;

    /**
     * Creates a new CsvRepository.
     */
    private CsvRepository() {
        super(new CsvDataSource());
    }

    /**
     * Returns the instance of the CsvRepository class.
     *
     * @return The instance of the CsvRepository class
     */
    public static synchronized CsvRepository getInstance() {
        if (instance == null) {
            instance = new CsvRepository();
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
    ArticleModel adapt(CsvDataEntity dataEntity) {
        return new ArticleModel(
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
     * @return List of articles
     */
    @Override
    List<ArticleModel> pullData(Context context) throws Exception {
        return dataSource.get(context)
                .stream()
                .map(this::adapt)
                .collect(Collectors.toList());
    }
}
