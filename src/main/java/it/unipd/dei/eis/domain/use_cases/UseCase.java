package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.controllers.Controller;
import it.unipd.dei.eis.domain.controllers.ControllerExecutor;
import it.unipd.dei.eis.presentation.Context;

/**
 * UseCase is the abstract class for all the use cases.
 * It contains the controllers that are executed by the use case.
 */
public abstract class UseCase {

    /**
     * The controllers that are executed by the use case.
     */
    private final Controller[] controllers;

    /**
     * UseCase constructor.
     *
     * @param controllers the controllers that are executed by the use case
     */
    UseCase(Controller[] controllers) {
        this.controllers = controllers;
    }

    /**
     * UseCase constructor.
     *
     * @param controller the controller that is executed by the use case
     */
    UseCase(Controller controller) {
        controllers = new Controller[]{controller};
    }

    /**
     * Executes the use case.
     *
     * @param context the context
     */
    public void run(Context context) {
        for (Controller controller : controllers) {
            ControllerExecutor.getInstance()
                    .execute(controller, context);
        }
    }
}
