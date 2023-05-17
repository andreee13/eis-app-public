package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.errors.Failure;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.domain.models.IModel;
import it.unipd.dei.eis.domain.repositories.RepositoryFactory;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;

public class TermsExtractor extends UseCase {
    @Override
    @SuppressWarnings("unchecked")
    public void execute(Context context) {
        Either<Failure, ? extends List<? extends IModel>> result = RepositoryFactory.create(context.source).fetch(context);
        if (result.isFailure()) {
            System.out.println(result.failure.message);
        } else {
            List<Article> articles = (List<Article>) result.success;
            //TODO
        }
    }
}
