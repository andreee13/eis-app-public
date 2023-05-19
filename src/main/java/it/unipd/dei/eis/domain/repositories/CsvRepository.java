package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.data.entities.CsvDataEntity;
import it.unipd.dei.eis.data.sources.CsvDataSource;
import it.unipd.dei.eis.domain.models.Article;

import java.util.List;
import java.util.stream.Collectors;

public class CsvRepository extends Repository<CsvDataSource, Article> {

    CsvRepository() {
        super(new CsvDataSource());
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
    public Either<Failure, List<Article>> pull(Context context) {
        try {
            return Either.success(dataSource.get(context).stream().map(this::resultToArticle).collect(Collectors.toList()));
        } catch (Exception e) {
            return Either.failure(new Failure(e));
        }
    }
}
