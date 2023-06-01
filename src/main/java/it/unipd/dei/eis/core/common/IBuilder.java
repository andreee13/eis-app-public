package it.unipd.dei.eis.core.common;

/**
 * The IBuilder interface is the interface of the builder pattern.
 *
 * @param <T> the type of the object to be built
 */
public interface IBuilder<T> {

    /**
     * Builds the object.
     *
     * @return the object
     */
    T build();
}
