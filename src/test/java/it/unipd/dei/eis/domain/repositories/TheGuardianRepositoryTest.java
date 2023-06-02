package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.utils.ContextBuilder;
import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.domain.models.ArticleModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void pull() {
        List<ArticleModel> result = repository.pull(context);
        assertEquals(result.size(), context.countArticles);
    }
}