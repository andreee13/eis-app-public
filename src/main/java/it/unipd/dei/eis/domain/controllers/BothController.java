package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.domain.use_cases.ArticlesDownloader;
import it.unipd.dei.eis.domain.use_cases.TermsExtractor;
import it.unipd.dei.eis.domain.use_cases.UseCase;

public class BothController extends Controller {
    public BothController() {
        super(new UseCase[]{new ArticlesDownloader(), new TermsExtractor()});
    }
}
