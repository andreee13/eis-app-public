package it.unipd.dei.eis.domain.repositories;

import it.unipd.dei.eis.core.utils.Either;
import it.unipd.dei.eis.core.utils.Failure;
import it.unipd.dei.eis.domain.models.Article;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.presentation.ContextBuilder;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("csv-repository")
public class CsvRepositoryTest {
    private final CsvRepository repository = new CsvRepository();

    @Test
    void testPull() {
        Context context = new ContextBuilder()
                .setSource("file.csv")
                .setCountArticles(10)
                .setCommand("download")
                .build();
        Either<Failure, List<Article>> result = repository.pull(context);
        assertTrue(result.isSuccess());
    }
}