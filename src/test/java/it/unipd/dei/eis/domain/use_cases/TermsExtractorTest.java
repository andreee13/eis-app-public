package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.constants.UseCaseConstants;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.presentation.ContextBuilder;
import org.junit.Test;

/**
 * Test the terms extractor use case.
 */
public class TermsExtractorTest {

    /**
     * Test the terms extractor use case.
     */
    @Test
    public void run() {
        Context context = new ContextBuilder()
                .setCommand(UseCaseConstants.EXTRACT)
                .setSource("articles.json")
                .setCountTerms(10)
                .setCountArticles(10)
                .build();
        UseCaseFactory.create(context.command)
                .run(context);
    }
}