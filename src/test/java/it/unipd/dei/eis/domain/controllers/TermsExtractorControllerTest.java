package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.enums.UseCases;
import it.unipd.dei.eis.core.utils.ContextBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the TermsExtractorController class.
 */
public class TermsExtractorControllerTest {

    /**
     * Test the terms extractor controller.
     */
    @Test
    public void execute() {
        Context context1 = new ContextBuilder()
                .setUseCase(UseCases.EXTRACT)
                .setSource("src/test/resources/articles.json")
                .setOutputTerms("terms.txt")
                .setCountTerms(10)
                .setCountArticles(10)
                .setLemma(true)
                .build();
        assertTrue(new TermsExtractorController().execute(context1).isSuccess());
        Context context2 = new ContextBuilder()
                .setUseCase(UseCases.EXTRACT)
                .setSource("src/test/resources/articles.xml")
                .setOutputTerms("terms.txt")
                .setCountTerms(10)
                .setCountArticles(10)
                .build();
        assertTrue(new TermsExtractorController().execute(context2).isFailure());
    }
}