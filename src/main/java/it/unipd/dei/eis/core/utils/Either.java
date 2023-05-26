package it.unipd.dei.eis.core.utils;

/**
 * A class that represents an Either type.
 *
 * @param <Failure> The type of the failure
 * @param <T>       The type of the success
 */
public class Either<Failure, T> {
    public final Failure failure;
    public final T success;

    /**
     * Creates a new Either.
     *
     * @param failure The failure
     * @param success The success
     */
    private Either(Failure failure, T success) {
        this.failure = failure;
        this.success = success;
    }

    /**
     * Creates a new Either (Failure).
     *
     * @param failure The failure
     * @return The new Either
     */
    public static <Failure, T> Either<Failure, T> failure(Failure failure) {
        return new Either<>(failure, null);
    }

    /**
     * Creates a new Either (Success).
     *
     * @param success The success
     * @return The new Either
     */
    public static <Failure, T> Either<Failure, T> success(T success) {
        return new Either<>(null, success);
    }

    /**
     * Check if an Either is a Failure.
     *
     * @return true if the Either is a Failure, false otherwise
     */
    public boolean isFailure() {
        return failure != null;
    }

    /**
     * Check if an Either is a Success.
     *
     * @return true if the Either is a Success, false otherwise
     */
    public boolean isSuccess() {
        return success != null;
    }
}