package br.com.unipac.divan.divanapi.model.service;

import br.com.unipac.divan.divanapi.api.dto.request.login.AuthenticationRequest;
import br.com.unipac.divan.divanapi.api.dto.response.login.LoginResponse;

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
    LoginResponse validate(AuthenticationRequest authenticationRequest) throws Exception;
}
