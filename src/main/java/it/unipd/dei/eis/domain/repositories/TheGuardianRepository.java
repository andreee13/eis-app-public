package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.data.entities.TheGuardianDataEntity;
import it.unipd.dei.eis.data.sources.TheGuardianDataSource;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A repository for models from a data source.
 */
public class TheGuardianRepository extends Repository<TheGuardianDataSource, TheGuardianDataEntity, Article> {

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
     * @return Either a Failure or a List of Articles
     */
    @Override
    public Either<Failure, List<Article>> pull(Context context) {
        try {
            return Either.success(
                    dataSource.get(context)
                            .stream()
                            .collect(
                                    ArrayList::new,
                                    (list, response) -> list.addAll(
                                            response.response.results.stream()
                                                    .map(
                                                            (result) -> new Article(
                                                                    result.id,
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
                            )
            );
        } catch (Exception e) {
            return Either.failure(new Failure(e, "Failed to get data from data source"));
        }
    }
}
