package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.errors.Failure;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.data.entities.CsvDataEntity;
import it.unipd.dei.eis.data.sources.CsvDataSource;
import it.unipd.dei.eis.domain.models.Article;
import org.apache.commons.cli.CommandLine;

import java.util.List;
import java.util.stream.Collectors;

public class CsvRepository extends Repository<CsvDataSource, Article> {

    public CsvRepository(CommandLine cmd) {
        super(cmd, new CsvDataSource());
    }

    private Article resultToArticle(CsvDataEntity result) {
        return new Article(
                result.identifier,
                result.title,
                result.body,
                result.url,
                result.date,
                dataSource.id
        );
    }

    @Override
    public Either<Failure, List<Article>> fetch(CommandLine cmd) {
        try {
            return Either.success(dataSource.get(cmd).stream().map(this::resultToArticle).collect(Collectors.toList()));
        } catch (Exception e) {
            return Either.failure(new Failure(e));
        }
    }
}
