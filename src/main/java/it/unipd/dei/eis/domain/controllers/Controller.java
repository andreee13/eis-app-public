package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.presentation.Context;

public abstract class Controller {
    public final String name;

    Controller(String name) {
        this.name = name;
    }

    public abstract Either<Failure, Success> execute(Context context);
}
