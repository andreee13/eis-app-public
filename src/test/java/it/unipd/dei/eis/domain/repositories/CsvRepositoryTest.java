package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.constants.UseCases;
import it.unipd.dei.eis.core.utils.ContextBuilder;
import it.unipd.dei.eis.domain.models.ArticleModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the csv repository.
 */
public class CsvRepositoryTest {

    /**
     * The repository to test.
     */
    private final CsvRepository repository = CsvRepository.getInstance();

    /**
     * The context to use.
     */
    private final Context context = new ContextBuilder()
            .setSource("src/test/resources/file.csv")
            .setOutputArticles("articles.json")
            .setCountArticles(1)
            .setUseCase(UseCases.DOWNLOAD)
            .build();

    /**
     * Test the pullData method.
     *
     * @throws Exception if an error occurs.
     */
    @Test
    void pullData() throws Exception {
        List<ArticleModel> result = repository.pullData(context);
        assertEquals(result.size(), context.countArticles);
    }
}