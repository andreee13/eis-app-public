package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.enums.UseCases;
import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.utils.ContextBuilder;
import org.junit.jupiter.api.Test;

/**
 * Test the DownloadController class.
 */
public class DownloadControllerTest {

    /**
     * Test the article downloader controller.
     */
    @Test
    public void execute() {
        Context context = new ContextBuilder()
                .setUseCase(UseCases.DOWNLOAD)
                .setSource("src/test/resources/file.csv")
                .setCountArticles(10)
                .build();
        new DownloadController().execute(context);
    }
}