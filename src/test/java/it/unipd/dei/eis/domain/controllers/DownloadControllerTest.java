package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.enums.UseCases;
import it.unipd.dei.eis.core.utils.ContextBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the DownloadController class.
 */
public class DownloadControllerTest {

    /**
     * Test the article downloader controller.
     */
    @Test
    public void execute() {
        Context context1 = new ContextBuilder()
                .setUseCase(UseCases.DOWNLOAD)
                .setSource("src/test/resources/file.csv")
                .setOutputArticles("articles.json")
                .setCountArticles(10)
                .build();
        new DownloadController().execute(context1);
        Context context2 = new ContextBuilder()
                .setUseCase(UseCases.DOWNLOAD)
                .setSource("src/test/resources/file.xml")
                .setOutputArticles("articles.json")
                .setCountArticles(10)
                .build();
        assertTrue(new DownloadController().execute(context2).isFailure());
    }
}