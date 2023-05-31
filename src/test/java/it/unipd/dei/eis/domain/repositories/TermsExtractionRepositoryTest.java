package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.presentation.ContextBuilder;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the terms extraction repository.
 */
@Tag("TermsExtraction-repository")
public class TermsExtractionRepositoryTest {

    /**
     * The repository to test.
     */
    private final TermsExtractionRepository repository = new TermsExtractionRepository();

    /**
     * The context to use.
     */
    private final Context context = new ContextBuilder()
            .setSource("articles.json")
            .setCountArticles(1)
            .setCommand("extract")
            .build();

    /**
     * Test the push method.
     */
    @Test
    void testPush() {
        Either<Failure, Success> result = repository.push(context, Collections.singletonList(new Article("id",
                "title",
                "body",
                "url",
                DateParser.tryParse("2023-01-01"),
                "source")));
        assertTrue(result.isSuccess(), result.failure.message);
    }
}

