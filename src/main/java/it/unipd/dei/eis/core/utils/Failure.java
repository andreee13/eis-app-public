package it.unipd.dei.eis.core.utils;

public class Failure {
    public final String message;
    public final Exception exception;

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
}
