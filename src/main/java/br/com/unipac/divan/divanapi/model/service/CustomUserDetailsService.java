package br.com.unipac.divan.divanapi.model.service;

import br.com.unipac.divan.divanapi.model.entities.user.User;

/**
 * The interface Custom user details service.
 */
public interface CustomUserDetailsService {
    /**
     * Load current user user.
     *
     * @return the user
     */
    User loadCurrentUser();
}
