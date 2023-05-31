package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.constants.UseCaseConstants;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.presentation.ContextBuilder;
import org.junit.jupiter.api.Test;

public class TermsExtractorControllerTest {

    /**
     * Test the terms extractor controller.
     */
    @Test
    public void execute() {
        Context context = new ContextBuilder()
                .setCommand(UseCaseConstants.EXTRACT)
                .setSource("src/test/resources/articles.json")
                .setOutput("src/test/resources/articles")
                .setCountTerms(10)
                .setCountArticles(10)
                .build();
        new TermsExtractorController().execute(context);
    }
}