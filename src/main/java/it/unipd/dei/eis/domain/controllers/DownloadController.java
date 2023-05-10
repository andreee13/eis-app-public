package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.domain.use_cases.ArticlesDownloader;
import it.unipd.dei.eis.domain.use_cases.UseCase;
import it.unipd.dei.eis.presentation.Argument;

import java.util.List;

public class DownloadController extends Controller {
    public DownloadController(List<Argument> arguments) {
        super(arguments, new UseCase[]{new ArticlesDownloader(arguments)});
    }

    @Override
    public void run() {
        for (UseCase useCase : useCases) {
            useCase.execute();
        }
    }
}
