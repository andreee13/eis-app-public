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
     * Test the pullData method.
     */
    @Test
    void pullData() throws Exception {
        List<ArticleModel> result = repository.pullData(context);
        assertEquals(result.size(), context.countArticles);
        assertEquals(result.get(0).id, 1121452019);
    }

    /**
     * Test the pushData method.
     */
    @Test
    void pushData() throws Exception {
        List<ArticleModel> result = repository.pullData(context);
        repository.pushData(context, result);
    }
}