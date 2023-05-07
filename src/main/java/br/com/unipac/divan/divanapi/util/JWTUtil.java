package br.com.unipac.divan.divanapi.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Strings;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * The type Jwt util.
 */
@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JWTUtil {
    /**
     * The constant EXPIRATION_TIME.
     */
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    //= 900_000; // 15 mins
    // 60_000;//
    /**
     * The constant SECRET.
     */
    public static final String SECRET = "YVbia7nSqgXMy2mdpwRK5ZqgTV256dtd4vtNPvi9BrEPZu7MAxm6Q43t6q9TCGMPYV6ieDNztvb4H7UX3AfvkiE7rgjdqGjmq4V54mkWa3yndHHxfJqSQhcJe3VgrPY2VntFQXTc8NWb99z6BbCWQWy8eKgm7rz7MqneX9yAhumvn9QHq8nkzzDGZ6BxrtChMDC5g4rhxqL2nVWvJfdkFSmmpVLiSvXBLSKkUqXnk9VqwyEyXLMMf8xkhEb2AKjj";
    /**
     * The constant TOKEN_PREFIX.
     */
    public static final String TOKEN_PREFIX = "Bearer";
    /**
     * The constant HEADER_STRING.
     */
    public static final String HEADER_STRING = "Authorization";

    //private JWTUtil() {
    // throw new NotImplementationConstructionException("Classe não pode ser instanciada");
    //}

    /**
     * Create token string.
     *
     * @param email the email
     * @return the string
     */
    public static String createToken(String email) {
        return getToken(email);
    }

    private static String getToken(String email) {
        try {

            Date date = new Date(System.currentTimeMillis() + JWTUtil.EXPIRATION_TIME);
            log.info("Data com token: " + date);
            SignatureAlgorithm hs512 = SignatureAlgorithm.HS512;
            log.info("SignatureAlgorithm: " + hs512);
            String secret = JWTUtil.SECRET;
            log.info("SignatureAlgorithm: " + secret);

            String jwtToken = Jwts.builder().setSubject(email)
                    .setExpiration(date)
                    .signWith(hs512, secret).compact();

            String token = JWTUtil.TOKEN_PREFIX + " " + jwtToken;
            return token;

        } catch (Exception ex) {
            log.info("Erro ao criar token: " + ex.getMessage());
            return null;
        }
    }
}
