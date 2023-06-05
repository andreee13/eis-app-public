package it.unipd.dei.eis.core.constants;

import org.apache.commons.cli.CommandLine;

/**
 * Enumeration of the use cases.
 */
public enum UseCases {

    /**
     * The use case for downloading articles.
     */
    DOWNLOAD,

    /**
     * The use case for extracting terms.
     */
    EXTRACT,

    /**
     * The use case for both downloading and extracting articles.
     */
    DOWNLOAD_AND_EXTRACT;

    public static UseCases fromCommandLine(CommandLine commandLine) {
        if (commandLine.hasOption("d") && commandLine.hasOption("e")) {
            return DOWNLOAD_AND_EXTRACT;
        } else if (commandLine.hasOption("d")) {
            return DOWNLOAD;
        } else if (commandLine.hasOption("e")) {
            return EXTRACT;
        }
        return DOWNLOAD_AND_EXTRACT;
    }
}
