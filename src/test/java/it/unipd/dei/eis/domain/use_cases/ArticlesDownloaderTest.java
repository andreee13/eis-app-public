package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.constants.UseCaseConstants;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.presentation.ContextBuilder;
import org.junit.jupiter.api.Test;

public class ArticlesDownloaderTest {

    /**
     * Test the article downloader use case.
     */
    @Test
    public void run() {
        Context context = new ContextBuilder()
                .setCommand(UseCaseConstants.DOWNLOAD)
                .setSource("file.csv")
                .setCountArticles(10)
                .build();
        UseCaseFactory.create(context.command)
                .run(context);
    }
}