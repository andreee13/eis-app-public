package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.domain.use_cases.UseCase;
import org.apache.commons.cli.CommandLine;

public class TermsExtractorController extends Controller {

    public TermsExtractorController(CommandLine cmd) {
        super(cmd, new UseCase[]{});
    }
}
