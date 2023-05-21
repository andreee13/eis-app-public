package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.constants.CommandConstants;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.domain.models.IModel;
import it.unipd.dei.eis.domain.repositories.RepositoryFactory;
import it.unipd.dei.eis.presentation.Context;

import java.util.List;

public class TermsExtractor extends UseCase {
    @Override
    @SuppressWarnings("unchecked, rawtypes")
    public Either<Failure, Success> execute(Context context) {
        Either<Failure, ? extends List<? extends IModel>> result1 = RepositoryFactory.create(context.command.equals(CommandConstants.BOTH) ? "output.json" : context.source).pull(context);
        if (result1.isFailure()) {
            return Either.failure(result1.failure);
        }
        RepositoryFactory.create(context.command.equals(CommandConstants.BOTH) ? "extract" : context.command).push(context, (List) result1.success);
        return Either.success(new Success());
    }
}
