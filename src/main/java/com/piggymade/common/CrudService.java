package com.piggymade.common;


import java.util.Optional;

public interface CrudService<T, I> extends ReadOnlyService<T, I> {
    /**
     * Save a new entity or update an existing entity.
     *
     * @param entity the entity to save or update
     * @return the saved or updated entity
     */
    T save(T entity);


    /**
     * Update an entity.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    Optional<T>  update(T entity) ;

    /**
     * Delete an entity by its ID.
     *
     * @param id the ID of the entity to delete
     * @return if success or failed to delete
     */
    boolean deleteById(String id);
    boolean deleteById(I id);

    long count();
}
