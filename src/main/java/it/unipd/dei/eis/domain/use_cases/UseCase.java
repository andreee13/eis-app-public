package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.controllers.Controller;
import it.unipd.dei.eis.presentation.Context;

public abstract class UseCase {
    final Controller[] controllers;

    protected UseCase(Controller[] controllers) {
        this.controllers = controllers;
    }

    public void run(Context context) {
        for (Controller controller : controllers) {
            if (controller.execute(context).isFailure()) {
                //TODO: log failure
                return;
            }
        }
    }
}
