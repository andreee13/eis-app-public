package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.constants.CommandConstants;

public interface ControllerFactory {
    static Controller create(String command) throws IllegalArgumentException {
        switch (command) {
            case CommandConstants.DOWNLOAD:
                return new DownloadController();
            case CommandConstants.EXTRACT:
                return new TermsExtractorController();
            case CommandConstants.BOTH:
                return new BothController();
            default:
                throw new IllegalArgumentException("Invalid command");
        }
    }
}
