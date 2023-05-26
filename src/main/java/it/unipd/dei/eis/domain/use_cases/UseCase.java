package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.controllers.Controller;
import it.unipd.dei.eis.domain.controllers.ControllerExecutor;
import it.unipd.dei.eis.presentation.Context;

public abstract class UseCase {
    final Controller[] controllers;

    UseCase(Controller[] controllers) {
        this.controllers = controllers;
    }

    UseCase(Controller controller) {
        controllers = new Controller[]{controller};
    }

    public void run(Context context) {
        for (Controller controller : controllers) {
            ControllerExecutor.getInstance().execute(controller, context);
        }
    }
}
