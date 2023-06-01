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
     * Creates a new Repository.
     */
    TheGuardianRepository() {
        super(new TheGuardianDataSource());
    }

    /**
     * Pulls the articles from the data source.
     *
     * @param context The context to use
     * @return List of articles
     */
    @Override
    public List<ArticleModel> pull(Context context) {
        return dataSource.get(context)
                .stream()
                .collect(
                        ArrayList::new,
                        (list, response) -> list.addAll(
                                response.response.results.stream()
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
}
