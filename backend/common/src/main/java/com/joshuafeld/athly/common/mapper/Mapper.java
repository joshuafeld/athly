package com.joshuafeld.athly.common.mapper;

/**
 * A data transfer object mapper.
 *
 * @param <E> the entity type
 * @param <D> the data transfer object type
 */
public interface Mapper<E, D> {

    /**
     * Converts an entity to its respective data transfer object.
     *
     * @param entity the entity
     * @return the data transfer object
     */
    D toDto(final E entity);
}
