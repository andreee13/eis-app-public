package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.controllers.Controller;
import it.unipd.dei.eis.domain.controllers.TermsExtractorController;

public class TermsExtractor extends UseCase {

    protected TermsExtractor() {
        super(new Controller[]{new TermsExtractorController()});
    }
}
