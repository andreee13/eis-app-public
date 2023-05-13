package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.errors.Failure;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.data.entities.TheGuardianIDataEntity;
import it.unipd.dei.eis.data.sources.TheGuardianDataSource;
import it.unipd.dei.eis.domain.models.Article;
import org.apache.commons.cli.CommandLine;

import java.util.List;
import java.util.stream.Collectors;

public class TheGuardianRepository extends Repository<TheGuardianDataSource, Article> {
    public TheGuardianRepository(CommandLine cmd) {
        super(cmd, new TheGuardianDataSource());
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
    public Either<Failure, List<Article>> fetch(CommandLine cmd) {
        try {
            return Either.success(dataSource.get(cmd).get(0).response.results.stream().map(this::resultToArticle).collect(Collectors.toList()));
        } catch (Exception e) {
            return Either.failure(new Failure(e));
        }
    }
}
