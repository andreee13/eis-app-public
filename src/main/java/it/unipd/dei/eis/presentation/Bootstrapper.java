package it.unipd.dei.eis.presentation;

import it.unipd.dei.eis.domain.controllers.ControllerFactory;

import java.util.ArrayList;

public class Bootstrapper {
    private final String[] args;

    public Bootstrapper(String[] args) {
        this.args = args;
    }

    public void launch() {
        ArrayList<Argument> arguments = parseArguments();
        if (!verifyArguments(arguments)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        ControllerFactory.create(arguments).run();
    }

    private ArrayList<Argument> parseArguments() {
        return new ArrayList<>();
    }

    private boolean verifyArguments(ArrayList<Argument> arguments) {
        return true;
    }
}
