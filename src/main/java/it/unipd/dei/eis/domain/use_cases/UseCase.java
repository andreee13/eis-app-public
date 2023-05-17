package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.presentation.Context;

public abstract class UseCase {
    public abstract void execute(Context context);
}
