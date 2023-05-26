package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.constants.UseCaseConstants;

public interface UseCaseFactory {
    static UseCase create(String command) throws IllegalArgumentException {
        switch (command) {
            case UseCaseConstants.DOWNLOAD:
                return new ArticlesDownloader();
            case UseCaseConstants.EXTRACT:
                return new TermsExtractor();
            case UseCaseConstants.BOTH:
                return new DownloadAndExtract();
            default:
                throw new IllegalArgumentException("Invalid command");
        }
    }
}
