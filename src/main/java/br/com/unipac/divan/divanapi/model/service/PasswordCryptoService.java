package br.com.unipac.divan.divanapi.model.service;

/**
 * The interface Password crypto service.
 */
public interface PasswordCryptoService {
    /**
     * Encrypt string.
     *
     * @param password the password
     * @return the string
     */
    String encrypt(String password);

    /**
     * Matches boolean.
     *
     * @param rawPassword     the raw password
     * @param encodedPassword the encoded password
     * @return the boolean
     */
    boolean matches(String rawPassword, String encodedPassword);
}
