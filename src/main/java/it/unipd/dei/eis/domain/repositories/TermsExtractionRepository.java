package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.data.entities.ArticleTermsDataEntity;
import it.unipd.dei.eis.data.sources.TermsDataSource;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;
import java.util.stream.Collectors;

public class TermsExtractionRepository extends Repository<TermsDataSource, Article> {
    TermsExtractionRepository() {
        super(new TermsDataSource());
    }

    private ArticleTermsDataEntity articleToResult(Article article) {
        return new ArticleTermsDataEntity(
                article.title,
                article.body
        );
    }

    @Override
    public Either<Failure, Success> push(Context context, List<Article> models) {
        try {
            dataSource.set(context, models.stream().map(this::articleToResult).collect(Collectors.toList()));
            return Either.success(new Success());
        } catch (Exception e) {
            return Either.failure(new Failure(e));
        }
    }
}
