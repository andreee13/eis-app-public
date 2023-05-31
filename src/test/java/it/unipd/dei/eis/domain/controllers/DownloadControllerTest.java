package it.unipd.dei.eis.domain.controllers;

import it.unipd.dei.eis.core.constants.UseCaseConstants;
import it.unipd.dei.eis.presentation.Context;
import it.unipd.dei.eis.presentation.ContextBuilder;
import org.junit.jupiter.api.Test;

public class DownloadControllerTest {

    /**
     * Test the article downloader controller.
     */
    @Test
    public void execute() {
        Context context = new ContextBuilder()
                .setCommand(UseCaseConstants.DOWNLOAD)
                .setSource("file.csv")
                .setCountArticles(10)
                .build();
        new DownloadController().execute(context);
    }
}