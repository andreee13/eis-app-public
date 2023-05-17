package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.domain.use_cases.UseCase;
import it.unipd.dei.eis.presentation.Context;

public abstract class Controller {
    final UseCase[] useCases;

    public Controller(UseCase[] useCases) {
        this.useCases = useCases;
    }

    public void run(Context context) {
        for (UseCase useCase : useCases) {
            useCase.execute(context);
        }
    }
}
