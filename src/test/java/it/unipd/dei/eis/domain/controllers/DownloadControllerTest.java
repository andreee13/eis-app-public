package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.constants.UseCases;
import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.utils.ContextBuilder;
import org.junit.jupiter.api.Test;

public class DownloadControllerTest {

    /**
     * Test the article downloader controller.
     */
    @Test
    public void execute() {
        Context context = new ContextBuilder()
                .setCommand(UseCases.DOWNLOAD)
                .setSource("file.csv")
                .setCountArticles(10)
                .build();
        new DownloadController().execute(context);
    }
}