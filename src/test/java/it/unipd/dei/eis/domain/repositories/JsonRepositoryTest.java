package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.constants.UseCases;
import it.unipd.dei.eis.core.utils.ContextBuilder;
import it.unipd.dei.eis.domain.models.ArticleModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the json repository.
 */
public class JsonRepositoryTest {

    /**
     * The repository to test.
     */
    private final JsonRepository repository = JsonRepository.getInstance();

    /**
     * The context to use.
     */
    private final Context context = new ContextBuilder()
            .setSource("src/test/resources/articles.json")
            .setOutputArticles("articles.json")
            .setCountArticles(1)
            .setUseCase(UseCases.DOWNLOAD)
            .build();

    /**
     * Test the pull method.
     */
    @Test
    void pull() throws Exception {
        List<ArticleModel> result = repository.pull(context);
        assertEquals(result.size(), context.countArticles);
    }

    /**
     * Test the push method.
     */
    @Test
    void push() throws Exception {
        List<ArticleModel> result = repository.pull(context);
        repository.push(context, result);
    }
}