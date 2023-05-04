package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.api.dto.request.login.AuthenticationRequest;
import br.com.unipac.divan.divanapi.api.dto.response.login.LoginResponse;
import br.com.unipac.divan.divanapi.model.entities.user.User;
import br.com.unipac.divan.divanapi.model.service.AuthService;
import br.com.unipac.divan.divanapi.model.service.PasswordCryptoService;
import br.com.unipac.divan.divanapi.model.service.UserService;
import br.com.unipac.divan.divanapi.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Auth service.
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private UserDetailsService customUserDetailsService;
    private UserService userService;
    private PasswordCryptoService cryptoService;

    /**
     * Instantiates a new Auth service.
     *
     * @param customUserDetailsService the custom user details service
     * @param userService              the user service
     * @param cryptoService            the crypto service
     */
    public AuthServiceImpl(UserDetailsService customUserDetailsService, UserService userService, PasswordCryptoService cryptoService) {
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
        this.cryptoService = cryptoService;
    }

    @Override
    public LoginResponse validate(AuthenticationRequest authenticationRequest) throws Exception {
        validateLoginRequestIsNull(authenticationRequest);
        return resultLoginInfo(authenticationRequest);
    }

    private LoginResponse resultLoginInfo(AuthenticationRequest loginRequest) {
        UserDetails userResult = loadUserByUsername(loginRequest);

        if (userResult != null && !userResult.getUsername().isEmpty()) {
            Optional<User> user = userService.findByEmail(userResult.getUsername());
            if (user.isPresent()) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setToken(JWTUtil.createToken(user.get().getEmail()));
                loginResponse.setUserId(user.get().getId().longValue());
                loginResponse.setRoles(user.get().getProfiles());
                return loginResponse;
            }
        } else {
            //throw new ResourceFoundException("Usuário não existe na nossa base");
        }

        return new LoginResponse();
    }

    private UserDetails loadUserByUsername(AuthenticationRequest loginRequest) {
        return customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
    }

    private void validateLoginRequestIsNull(AuthenticationRequest loginRequest) throws Exception {
        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            throw new Exception("Please fill in username and password");
        }
    }

    private boolean validated(AuthenticationRequest authenticationRequest, String password) {
        return cryptoService.matches(authenticationRequest.getPassword(), password);
    }
}