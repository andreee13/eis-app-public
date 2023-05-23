package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.controllers.Controller;
import it.unipd.dei.eis.domain.controllers.DownloadController;

public class ArticlesDownloader extends UseCase {
    protected ArticlesDownloader() {
        super(new Controller[]{new DownloadController()});
    }
}