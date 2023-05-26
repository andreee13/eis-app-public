package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.controllers.DownloadController;

public class ArticlesDownloader extends UseCase {
    ArticlesDownloader() {
        super(new DownloadController());
    }
}