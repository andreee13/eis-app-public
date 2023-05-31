package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.presentation.ContextBuilder;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the csv repository.
 */
@Tag("csv-repository")
public class CsvRepositoryTest {

    /**
     * The repository to test.
     */
    private final CsvRepository repository = new CsvRepository();

    /**
     * The context to use.
     */
    private final Context context = new ContextBuilder()
            .setSource("file.csv")
            .setCountArticles(1)
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
        String articleId = "6760222a-a05c-48e9-8648-508490045d0e";
        assertEquals(articleId, result.success.get(0).id);

    }
}