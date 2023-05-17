package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.domain.use_cases.ArticlesDownloader;
import it.unipd.dei.eis.domain.use_cases.UseCase;

public class DownloadController extends Controller {
    public DownloadController() {
        super(new UseCase[]{new ArticlesDownloader()});
    }
}
