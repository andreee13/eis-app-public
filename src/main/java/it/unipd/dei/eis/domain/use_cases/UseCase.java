package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.presentation.Context;

public abstract class UseCase {
    public abstract Either<Failure, Success> execute(Context context);
}
