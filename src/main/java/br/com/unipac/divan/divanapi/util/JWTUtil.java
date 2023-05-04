package br.com.unipac.divan.divanapi.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Date;

/**
 * The type Jwt util.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JWTUtil {
    /**
     * The constant EXPIRATION_TIME.
     */
    public static final long EXPIRATION_TIME  = 864_000_000; // 10 days
        //= 900_000; // 15 mins
    // 60_000;//
    /**
     * The constant SECRET.
     */
    public static final String SECRET = "adonis-token";
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
        String jwtToken = Jwts.builder().setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + JWTUtil.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, JWTUtil.SECRET).compact();
        return JWTUtil.TOKEN_PREFIX + " " + jwtToken;
    }

}
