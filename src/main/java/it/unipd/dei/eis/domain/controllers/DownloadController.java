package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.domain.use_cases.ArticlesDownloader;
import it.unipd.dei.eis.domain.use_cases.UseCase;
import org.apache.commons.cli.CommandLine;

public class DownloadController extends Controller {
    public DownloadController(CommandLine cmd) {
        super(cmd, new UseCase[]{new ArticlesDownloader(cmd)});
    }
}
