package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.domain.use_cases.UseCase;
import org.apache.commons.cli.CommandLine;

public abstract class Controller {
    protected final CommandLine cmd;

    protected final UseCase[] useCases;

    public Controller(CommandLine cmd, UseCase[] useCases) {
        this.cmd = cmd;
        this.useCases = useCases;
    }

    public void run() {
        for (UseCase useCase : useCases) {
            useCase.execute();
        }
    }
}
