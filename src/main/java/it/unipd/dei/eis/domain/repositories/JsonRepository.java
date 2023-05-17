package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.errors.Failure;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.data.entities.JsonDataEntity;
import it.unipd.dei.eis.data.sources.JsonDataSource;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;
import java.util.stream.Collectors;

public class JsonRepository extends Repository<JsonDataSource, Article> {
    JsonRepository() {
        super(new JsonDataSource());
    }

    private Article resultToArticle(JsonDataEntity result) {
        return new Article(
                result.id,
                result.title,
                result.body,
                result.url,
                result.date,
                dataSource.id
        );
    }

    @Override
    public Either<Failure, List<Article>> fetch(Context context) {
        try {
            return Either.success(dataSource.get(context).stream().map(this::resultToArticle).collect(Collectors.toList()));
        } catch (Exception e) {
            return Either.failure(new Failure(e));
        }
    }
}
