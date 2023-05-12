package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.constants.CommandConstants;
import org.apache.commons.cli.CommandLine;

public class ControllerFactory {
    private ControllerFactory() {
    }

    public static Controller create(String command, CommandLine cmd) {
        switch (command) {
            case CommandConstants.DOWNLOAD:
                return new DownloadController(cmd);
            case CommandConstants.EXTRACT:
                return new TermsExtractorController(cmd);
            default:
                throw new IllegalArgumentException("Invalid argument");
        }
    }
}
