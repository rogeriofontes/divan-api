package br.com.unipac.divan.divanapi.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * The interface Base service.
 *
 * @param <E> the type parameter
 * @param <I> the type parameter
 * @author alan.franco
 */
public interface BaseService<E, I extends Serializable> {
    /**
     * Save e.
     *
     * @param e the e
     * @return the e
     */
    E save(E e) throws Exception;

    /**
     * Edit e.
     *
     * @param id the id
     * @param e  the e
     * @return the e
     */
    E edit(I id, E e);

    /**
     * List all list.
     *
     * @return the list
     */
    List<E> listAll();

    /**
     * Find all pageable page.
     *
     * @param pageable the pageable
     * @return the page
     */
    Page<E> findAllPageable(Pageable pageable);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<E> findById(I id);

    /**
     * Remove boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean remove(I id);
}
