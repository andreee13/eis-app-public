package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.constants.CommandConstants;
import it.unipd.dei.eis.core.utils.Factory;
import org.apache.commons.cli.CommandLine;

public class ControllerFactory extends Factory<Controller> {
    public ControllerFactory(CommandLine cmd) {
        super(cmd);
    }

    @Override
    public Controller create(String name) throws IllegalArgumentException {
        switch (name) {
            case CommandConstants.DOWNLOAD:
                return new DownloadController(cmd);
            case CommandConstants.EXTRACT:
                return new TermsExtractorController(cmd);
            default:
                throw new IllegalArgumentException("Invalid command");
        }
    }
}
