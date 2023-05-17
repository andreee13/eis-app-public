package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.constants.CommandConstants;

public class ControllerFactory {
    public static Controller create(String command) throws IllegalArgumentException {
        switch (command) {
            case CommandConstants.DOWNLOAD:
                return new DownloadController();
            case CommandConstants.EXTRACT:
                return new TermsExtractorController();
            default:
                throw new IllegalArgumentException("Invalid command");
        }
    }
}
