package it.unipd.dei.eis.core.utils;

/**
 * A class that represents a failure.
 */
public class Failure {

    /**
     * The message of the failure.
     */
    public final String message;

    /**
     * The exception that caused the failure.
     */
    public final Exception exception;

    /**
     * Creates a new failure.
     *
     * @param exception the exception that caused the failure
     * @param message the message of the failure
     */
    public Failure(Exception exception, String message) {
        this.exception = exception;
        this.message = message;
    }

    /**
     * Creates a new failure.
     *
     * @param message the message of the failure
     */
    public Failure(String message) {
        this.exception = null;
        this.message = message;
    }

    /**
     * Creates a new failure.
     *
     * @param exception the exception that caused the failure
     */
    public Failure(Exception exception) {
        this.exception = exception;
        this.message = exception.getMessage();
    }
}
