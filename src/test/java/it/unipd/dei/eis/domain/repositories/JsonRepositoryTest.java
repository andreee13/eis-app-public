package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.core.utils.Success;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.presentation.ContextBuilder;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the json repository.
 */
@Tag("Json-repository")
public class JsonRepositoryTest {

    /**
     * The repository to test.
     */
    private final JsonRepository repository = new JsonRepository();

    /**
     * The context to use.
     */
    private final Context context = new ContextBuilder()
            .setSource("articles.json")
            .setCountArticles(1)
            .setCommand("download")
            .build();

    /**
     * Test the pull method.
     */
    @Test
    void testPull() {
        Either<Failure, List<Article>> result = repository.pull(context);
        assertTrue(result.isSuccess(), result.failure.message);
        assertEquals(result.success.size(), context.countArticles);
        String articleId = "6760222a-a05c-48e9-8648-508490045d0e";
        assertEquals(articleId, result.success.get(0).id);
    }

    /**
     * Test the push method.
     */
    @Test
    void testPush() {
        Either<Failure,List<Article>> result =repository.pull(context);
        Either<Failure, Success> result1 = repository.push(context, result.success);
        assertTrue(result1.isSuccess(), result.failure.message);
    }
}