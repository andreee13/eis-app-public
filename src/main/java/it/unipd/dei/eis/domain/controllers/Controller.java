package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.common.Either;
import it.unipd.dei.eis.core.common.Failure;
import it.unipd.dei.eis.core.common.Success;
import it.unipd.dei.eis.core.common.Context;

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
     * @return Either a Failure or a Success
     */
    public abstract Either<Failure, Success> execute(Context context);
}
