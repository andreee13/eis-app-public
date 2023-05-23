package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.constants.CommandConstants;

public interface UseCaseFactory {
    static UseCase create(String command) throws IllegalArgumentException {
        switch (command) {
            case CommandConstants.DOWNLOAD:
                return new ArticlesDownloader();
            case CommandConstants.EXTRACT:
                return new TermsExtractor();
            case CommandConstants.BOTH:
                return new DownloadAndExtract();
            default:
                throw new IllegalArgumentException("Invalid command");
        }
    }
}
