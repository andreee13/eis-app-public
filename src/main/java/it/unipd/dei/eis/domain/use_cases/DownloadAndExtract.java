package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.controllers.Controller;
import it.unipd.dei.eis.domain.controllers.DownloadController;
import it.unipd.dei.eis.domain.controllers.TermsExtractorController;

public class DownloadAndExtract extends UseCase {
    DownloadAndExtract() {
        super(new Controller[]{new DownloadController(), new TermsExtractorController()});
    }
}
