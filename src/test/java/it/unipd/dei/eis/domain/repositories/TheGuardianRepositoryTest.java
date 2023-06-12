package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.enums.UseCases;
import it.unipd.dei.eis.core.utils.ContextBuilder;
import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.domain.models.ArticleModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test the theguardian repository.
 */
public class TheGuardianRepositoryTest {

    /**
     * The repository to test.
     */
    private final TheGuardianRepository repository = TheGuardianRepository.getInstance();

    /**
     * The context to use.
     */
    private final Context context = new ContextBuilder()
            .setSource("theguardian")
            .setCountArticles(1)
            .setQuery("nuclear power")
            .setToDate(DateParser.tryParse("2023-01-01"))
            .setFromDate(DateParser.tryParse("2023-01-01"))
            .setUseCase(UseCases.DOWNLOAD)
            .build();

    /**
     * Test the pullData method.
     *
     * @throws Exception if an error occurs
     */
    @Test
    void pullData() throws Exception {
        List<ArticleModel> result = repository.pullData(context);
        assertEquals(result.size(), context.countArticles);
        assertEquals(-33595425, result.get(0).id);
    }

    /**
     * Test the pushData method.
     */
    @Test
    void pushData() {
        assertThrows(UnsupportedOperationException.class, () -> repository.pushData(context, null));
    }
}
