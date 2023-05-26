package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.domain.controllers.TermsExtractorController;

/**
 * TermsExtractor is the use case for extracting the terms.
 */
public class TermsExtractor extends UseCase {
    TermsExtractor() {
        super(new TermsExtractorController());
    }
}
