package it.unipd.dei.eis.domain.use_cases;

import it.unipd.dei.eis.core.enums.UseCases;

/**
 * A factory for use cases.
 */
public interface UseCaseFactory {

    /**
     * Create the use case based on the command line argument.
     *
     * @param useCase the use case
     * @return the use case
     * @throws IllegalArgumentException if the command line argument is invalid
     */
    static UseCase create(UseCases useCase) throws IllegalArgumentException {
        switch (useCase) {
            case DOWNLOAD:
                return new ArticlesDownloader();
            case EXTRACT:
                return new TermsExtractor();
            case DOWNLOAD_AND_EXTRACT:
                return new DownloaderAndExtractor();
            default:
                throw new IllegalArgumentException(String.format("\"%s\" is not a valid option", useCase));
        }
    }
}
