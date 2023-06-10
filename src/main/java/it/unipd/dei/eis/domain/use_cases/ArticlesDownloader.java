package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.controllers.DownloadController;

/**
 * ArticlesDownloader is the use case to download articles.
 */
public class ArticlesDownloader extends UseCase {

    /**
     * ArticlesDownloader constructor.
     */
    ArticlesDownloader() {
        super(new DownloadController());
    }
}