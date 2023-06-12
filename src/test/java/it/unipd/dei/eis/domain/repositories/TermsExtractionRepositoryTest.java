package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.enums.UseCases;
import it.unipd.dei.eis.core.utils.ContextBuilder;
import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.domain.models.ArticleModel;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test the terms extraction repository.
 */
public class TermsExtractionRepositoryTest {

    /**
     * The repository to test.
     */
    private final TermsExtractionRepository repository = TermsExtractionRepository.getInstance();

    /**
     * The context to use.
     */
    private final Context context = new ContextBuilder()
            .setSource("src/test/resources/articles.json")
            .setCountArticles(10)
            .setOutputTerms("terms.txt")
            .setUseCase(UseCases.EXTRACT)
            .build();

    /**
     * Test the pushData method.
     *
     * @throws Exception if an error occurs.
     */
    @Test
    void pushData() throws Exception {
        repository.pushData(
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

    /**
     * Test the pullData method.
     */
    @Test
    void pullData() {
        assertThrows(UnsupportedOperationException.class, () -> repository.pullData(context));
    }
}

