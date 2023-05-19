package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Success;
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

    private JsonDataEntity articleToResult(Article article) {
        return new JsonDataEntity(
                article.id,
                article.title,
                article.body,
                article.url,
                article.date,
                article.source
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

    @Override
    public Either<Failure, Success> push(Context context, List<Article> articles) {
        try {
            dataSource.set(context, articles.stream().map(this::articleToResult).collect(Collectors.toList()));
            return Either.success(new Success());
        } catch (Exception e) {
            return Either.failure(new Failure(e));
        }
    }
}
