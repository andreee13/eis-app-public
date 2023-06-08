package it.unipd.dei.eis.data.entities;

import it.unipd.dei.eis.core.common.IFilterable;

import java.util.Date;

/**
 * An abstract filterable data entity.
 */
public abstract class DataEntity implements IFilterable {
    /**
     * Filters the object by a string.
     *
     * @param s the string to filter by
     * @return true if the object respects the filter, false otherwise
     */
    @Override
    public boolean contains(String s) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    /**
     * Filters the object by a date.
     *
     * @param date the date to filter from
     * @return true if the object respects the filter, false otherwise
     */
    @Override
    public boolean before(Date date) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    /**
     * Filters the object by a date.
     *
     * @param date the date to filter to
     * @return true if the object respects the filter, false otherwise
     */
    @Override
    public boolean after(Date date) {
        throw new UnsupportedOperationException("Unsupported operation");
    }
}
