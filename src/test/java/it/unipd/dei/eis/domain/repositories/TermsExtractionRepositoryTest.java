package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.utils.ContextBuilder;
import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.domain.models.ArticleModel;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * Test the terms extraction repository.
 */
public class TermsExtractionRepositoryTest {

    /**
     * The repository to test.
     */
    private final TermsExtractionRepository repository = new TermsExtractionRepository();

    /**
     * The context to use.
     */
    private final Context context = new ContextBuilder()
            .setSource("src/test/resources/articles.json")
            .setCountArticles(10)
            .setOutputTerms("terms.txt")
            .setCommand("extract")
            .build();

    /**
     * Test the push method.
     */
    @Test
    void testPush() throws Exception {
        repository.push(
                context,
                Collections.singletonList(
                        new ArticleModel(
                                "title",
                                "body",
                                "url",
                                DateParser.tryParse("2023-01-01"),
                                "source"
                        )
                )
        );
    }
}

