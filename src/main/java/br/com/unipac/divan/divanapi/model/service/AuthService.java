package br.com.unipac.divan.divanapi.model.service;

import br.com.unipac.divan.divanapi.model.entities.user.User;

import java.util.Optional;

/**
 * The interface Auth service.
 */
public interface AuthService {
    /**
     * Validate token response.
     *
     * @param loginRequest the login request
     * @return the token response
     * @throws ServletException the servlet exception
     */
    Optional<User> validate(User user) throws Exception;

    Optional<User> register(User user) throws Exception;
}
