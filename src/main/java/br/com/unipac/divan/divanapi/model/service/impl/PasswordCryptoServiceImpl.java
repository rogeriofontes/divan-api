package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.service.PasswordCryptoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type Password crypto service.
 */
@Slf4j
@Service
public class PasswordCryptoServiceImpl implements PasswordCryptoService {

    private PasswordEncoder passwordEncoder;

    /**
     * Instantiates a new Password crypto service.
     *
     * @param passwordEncoder the password encoder
     */
    public PasswordCryptoServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
