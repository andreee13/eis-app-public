package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.controllers.Controller;
import it.unipd.dei.eis.domain.controllers.DownloadController;
import it.unipd.dei.eis.domain.controllers.TermsExtractorController;

/**
 * DownloaderAndExtractor is the use case for downloading and extracting the terms.
 */
public class DownloaderAndExtractor extends UseCase {
    DownloaderAndExtractor() {
        super(new Controller[]{new DownloadController(), new TermsExtractorController()});
    }
}
