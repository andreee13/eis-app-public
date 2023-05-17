package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.domain.use_cases.TermsExtractor;
import it.unipd.dei.eis.domain.use_cases.UseCase;

public class TermsExtractorController extends Controller {
    public TermsExtractorController() {
        super(new UseCase[]{new TermsExtractor()});
    }
}
