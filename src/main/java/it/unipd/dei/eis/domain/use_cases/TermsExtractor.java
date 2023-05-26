package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.controllers.TermsExtractorController;

public class TermsExtractor extends UseCase {

    TermsExtractor() {
        super(new TermsExtractorController());
    }
}
