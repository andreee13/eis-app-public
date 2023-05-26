package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.presentation.Context;

/**
 * The Controller class is the abstract class for all the controllers.
 * It contains the name of the controller and the execute method.
 */
public abstract class Controller {

    /**
     * The name of the controller.
     */
    public final String name;

    /**
     * Controller constructor.
     *
     * @param name the name of the controller
     */
    Controller(String name) {
        this.name = name;
    }

    /**
     * The execute method is used to execute the controller.
     *
     * @param context the context of the controller
     * @return the result of the controller
     */
    public abstract Either<Failure, Success> execute(Context context);
}
