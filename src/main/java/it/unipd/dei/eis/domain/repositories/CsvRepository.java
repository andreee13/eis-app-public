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
     * Creates a new CsvRepository.
     */
    CsvRepository() {
        super(new CsvDataSource());
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
    public List<ArticleModel> pull(Context context) throws Exception {
        return dataSource.get(context)
                .stream()
                .map(this::adapt)
                .collect(Collectors.toList());
    }
}
