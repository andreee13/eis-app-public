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

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Json-repository")
public class JsonRepositoryTest {
    private final JsonRepository repository = new JsonRepository();
    private final Context context = new ContextBuilder()
            .setSource("articles.json")
            .setCountArticles(10)
            .setCommand("download")
            .build();

    @Test
    void testPull() {
        Either<Failure, List<Article>> result = repository.pull(context);
        assertTrue(result.isSuccess());
    }

    @Test
    void testPush() {
        Either<Failure,List<Article>> result =repository.pull(context);
        Either<Failure, Success> result1 = repository.push(context, result.success);
        assertTrue(result1.isSuccess());
    }
}