package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.data.entities.TheGuardianDataEntity;
import it.unipd.dei.eis.data.sources.TheGuardianDataSource;
import it.unipd.dei.eis.domain.models.ArticleModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A repository for models from a data source.
 */
public class TheGuardianRepository extends Repository<TheGuardianDataSource, TheGuardianDataEntity, ArticleModel> {

    /**
     * The instance of the TheGuardianRepository class.
     */
    private static TheGuardianRepository instance;

    /**
     * Creates a new Repository.
     */
    private TheGuardianRepository() {
        super(new TheGuardianDataSource());
    }

    /**
     * Returns the instance of the TheGuardianRepository class.
     *
     * @return The instance of the TheGuardianRepository class
     */
    public static synchronized TheGuardianRepository getInstance() {
        if (instance == null) {
            instance = new TheGuardianRepository();
        }
        return instance;
    }

    /**
     * Pulls the articles from the data source.
     * Custom implementation for TheGuardianDataSource.
     *
     * @param context The context to use
     * @return List of articles
     */
    @Override
    List<ArticleModel> pullData(Context context) throws Exception {
        return dataSource.get(context)
                .stream()
                .collect(
                        ArrayList::new,
                        (list, response) -> list.addAll(
                                response.response.results.parallelStream()
                                        .map(
                                                (result) -> new ArticleModel(
                                                        result.webTitle,
                                                        result.fields.bodyText,
                                                        result.webUrl,
                                                        result.webPublicationDate,
                                                        dataSource.id
                                                )
                                        )
                                        .collect(Collectors.toList())
                        ),
                        ArrayList::addAll
                );
    }

    /**
     * Operation not supported.
     *
     * @param context The context to use
     * @param models  The models to push
     * @throws UnsupportedOperationException if not implemented
     */
    @Override
    void pushData(Context context, List<ArticleModel> models) {
        throw new UnsupportedOperationException("Operation not supported");
    }
}
