package it.unipd.dei.eis.core.common;

import java.util.Date;

/**
 * The IFilterable interface is the interface of the filterable pattern.
 */
public interface IFilterable {

    /**
     * Filters the object by a string.
     *
     * @param s the string to filter by
     * @return true if the object respects the filter, false otherwise
     */
    boolean contains(String s);

    /**
     * Filters the object by a date.
     *
     * @param date the date to filter from
     * @return true if the object respects the filter, false otherwise
     */
    boolean before(Date date);

    /**
     * Filters the object by a date.
     *
     * @param date the date to filter to
     * @return true if the object respects the filter, false otherwise
     */
    boolean after(Date date);
}
