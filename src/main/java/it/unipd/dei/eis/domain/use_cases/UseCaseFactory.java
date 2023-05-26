package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.constants.UseCaseConstants;

/**
 * UseCaseFactory is the factory for the use cases.
 * It is used to create the use case based on the command line argument.
 */
public interface UseCaseFactory {

    /**
     * Create the use case based on the command line argument.
     *
     * @param command the command line argument
     * @return the use case
     * @throws IllegalArgumentException if the command line argument is invalid
     */
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
