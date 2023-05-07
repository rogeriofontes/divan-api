package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.api.dto.request.authentication.AuthenticationRequest;
import br.com.unipac.divan.divanapi.api.dto.response.authentication.AuthenticationResponse;
import br.com.unipac.divan.divanapi.model.entities.user.User;
import br.com.unipac.divan.divanapi.model.service.AuthService;
import br.com.unipac.divan.divanapi.model.service.PasswordCryptoService;
import br.com.unipac.divan.divanapi.model.service.UserService;
import br.com.unipac.divan.divanapi.util.PasswordStrengthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Optional<User> validate(User user) throws Exception {
        validateLoginRequestIsNull(user);
        return resultLoginInfo(user);
    }

    private Optional<User> resultLoginInfo(User user) {
        UserDetails userResult = loadUserByUsername(user);

        if (userResult != null && !userResult.getUsername().isEmpty()) {
            Optional<User> userFound = userService.findByEmail(userResult.getUsername());
            if (userFound.isPresent()) {
                AuthenticationResponse loginResponse = new AuthenticationResponse();
                return userFound;
            }
        } else {
            //throw new ResourceFoundException("Usuário não existe na nossa base");
        }

        return null;
    }

    @Override
    public Optional<User> register(User user) throws Exception {

        int passwordStrength = PasswordStrengthUtil.calculatePasswordStrength(user.getPassword());
        if (passwordStrength <= 1) {
            //throw new UserNotFoundException("A senha digitada é muito Fraca, favor usar Letras Maiúscula, minuscula, números e caracteres especiais");
        }

        log.info("Account Service: " + passwordStrength);

        user.setLastAccess(LocalDateTime.now());
        User result = userService.save(user);

        return Optional.of(result);
    }

    private UserDetails loadUserByUsername(User user) {
        return customUserDetailsService.loadUserByUsername(user.getEmail());
    }

    private void validateLoginRequestIsNull(User user) throws Exception {
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new Exception("Please fill in username and password");
        }
    }

    private boolean validated(AuthenticationRequest authenticationRequest, String password) {
        return cryptoService.matches(authenticationRequest.getPassword(), password);
    }
}