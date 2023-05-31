package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.presentation.ContextBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the theguardian repository.
 */
public class TheGuardianRepositoryTest {

    /**
     * The repository to test.
     */
    private final TheGuardianRepository repository = new TheGuardianRepository();

    /**
     * The context to use.
     */
    private final Context context = new ContextBuilder()
            .setSource("theguardian")
            .setCountArticles(1)
            .setQuery("nuclear power")
            .setToDate(DateParser.tryParse("2023-01-01"))
            .setFromDate(DateParser.tryParse("2023-01-01"))
            .setCommand("download")
            .build();

    /**
     * Test the pull method.
     */
    @Test
    void testPull() {
        Either<Failure, List<Article>> result = repository.pull(context);
        assertTrue(result.isSuccess());
        assertEquals(result.success.size(), context.countArticles);
        String articleId = "world/2023/jan/01/kim-jong-un-north-korea-exponentially-increase-nuclear-warhead-production";
        assertEquals(articleId, result.success.get(0).id);
    }
}