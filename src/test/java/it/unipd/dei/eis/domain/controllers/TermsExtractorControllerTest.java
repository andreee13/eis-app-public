package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.constants.UseCases;
import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.utils.ContextBuilder;
import org.junit.jupiter.api.Test;

public class TermsExtractorControllerTest {

    /**
     * Test the terms extractor controller.
     */
    @Test
    public void execute() {
        Context context = new ContextBuilder()
                .setUseCase(UseCases.EXTRACT)
                .setSource("src/test/resources/articles.json")
                .setOutputTerms("src/test/resources/terms.txt")
                .setCountTerms(10)
                .setCountArticles(10)
                .build();
        new TermsExtractorController().execute(context);
    }
}