package com.piggymade.common;


import com.piggymade.model.pageable.Pageable;

import java.util.List;
import java.util.Optional;

public interface  ReadOnlyService<T,I> {

    /**
     * Retrieve an entity by its ID.
     *
     * @param id the ID of the entity to retrieve
     * @return an Optional containing the entity if found, or empty if not
     */
    Optional<T> findById(I id);
    Optional<T> findById(String id);

    /**
     * Retrieve all entities.
     *
     * @return a list of all entities
     */
    List<T> findAll();

    Pageable<T> findPageable(int pageNumber, int pageSize);

}
