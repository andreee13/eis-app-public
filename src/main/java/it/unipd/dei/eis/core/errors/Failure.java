package it.unipd.dei.eis.core.errors;

public class Failure {
    private final String message;
    private final Exception exception;

    public Failure(Exception exception, String message) {
        this.exception = exception;
        this.message = message;
    }

    public Failure(String message) {
        this.exception = null;
        this.message = message;
    }

    public Failure(Exception exception) {
        this.exception = exception;
        this.message = exception.getMessage();
    }

    public Exception getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}