package it.unipd.dei.eis.core.utils;

public class Either<Failure, T> {
    public final Failure failure;
    public final T success;

    private Either(Failure failure, T success) {
        this.failure = failure;
        this.success = success;
    }

    public static <Failure, T> Either<Failure, T> failure(Failure failure) {
        return new Either<>(failure, null);
    }

    public static <Failure, T> Either<Failure, T> success(T success) {
        return new Either<>(null, success);
    }

    public boolean isFailure() {
        return failure != null;
    }

    public boolean isSuccess() {
        return success != null;
    }
}