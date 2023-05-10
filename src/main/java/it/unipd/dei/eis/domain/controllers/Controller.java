package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.domain.use_cases.UseCase;
import it.unipd.dei.eis.presentation.Argument;

import java.util.List;

public abstract class Controller {
    final List<Argument> arguments;

    final UseCase[] useCases;

    public Controller(List<Argument> arguments, UseCase[] useCases) {
        this.arguments = arguments;
        this.useCases = useCases;
    }

    public abstract void run();
}
