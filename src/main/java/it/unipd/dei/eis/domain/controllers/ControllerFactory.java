package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.constants.CommandConstants;
import it.unipd.dei.eis.presentation.Argument;

import java.util.List;

public class ControllerFactory {
    public static Controller create(List<Argument> arguments) {
        switch (arguments.get(0).key) {
            case CommandConstants.DOWNLOAD:
                return new DownloadController(arguments);
            case CommandConstants.EXTRACT:
                return new TermsExtractorController(arguments);
            default:
                throw new IllegalArgumentException("Invalid argument");
        }
    }
}
