package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.errors.Failure;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.data.entities.TheGuardianIDataEntity;
import it.unipd.dei.eis.data.sources.TheGuardianDataSource;
import it.unipd.dei.eis.domain.models.Article;

import java.util.List;
import java.util.stream.Collectors;

public class TheGuardianRepository extends Repository<TheGuardianDataSource, Article> {
    TheGuardianRepository() {
        super(new TheGuardianDataSource());
    }

    private Article resultToArticle(TheGuardianIDataEntity.Response.Result result) {
        return new Article(
                result.id,
                result.webTitle,
                result.fields.bodyText,
                result.webUrl,
                result.webPublicationDate,
                dataSource.id
        );
    }

    @Override
    public Either<Failure, List<Article>> fetch(Context context) {
        try {
            return Either.success(dataSource.get(context).get(0).response.results.stream().map(this::resultToArticle).collect(Collectors.toList()));
        } catch (Exception e) {
            return Either.failure(new Failure(e));
        }
    }
}
