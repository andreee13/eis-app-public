package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
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
    void pull() throws Exception {
        List<ArticleModel> result = repository.pull(context);
        assertEquals(result.size(), context.countArticles);
    }
}