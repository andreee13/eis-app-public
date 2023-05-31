package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.constants.UseCaseConstants;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.presentation.ContextBuilder;
import org.junit.jupiter.api.Test;

public class DownloadAndExtractTest {

    /**
     * Test the download and extract use case.
     */
    @Test
    public void run() {
        Context context = new ContextBuilder()
                .setCommand(UseCaseConstants.BOTH)
                .setSource("file.csv")
                .setCountArticles(10)
                .setCountTerms(10)
                .build();
        UseCaseFactory.create(context.command)
                .run(context);
    }
}