package br.com.unipac.divan.divanapi.model.service;

import br.com.unipac.divan.divanapi.util.JWTUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * The type Token authentication service.
 */
@Slf4j
public class TokenAuthenticationService {

    /**
     * Add authentication.
     *
     * @param res      the res
     * @param username the username
     */
    public static void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + JWTUtil.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, JWTUtil.SECRET).compact();
        res.addHeader(JWTUtil.HEADER_STRING, JWTUtil.TOKEN_PREFIX + " " + JWT);
    }

    /**
     * Gets authentication.
     *
     * @param request the request
     * @return the authentication
     */
    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JWTUtil.HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser().setSigningKey(JWTUtil.SECRET)
                    .parseClaimsJws(token.replace(JWTUtil.TOKEN_PREFIX, "")).getBody().getSubject();

            if (user != null)
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
            else
                return null;
        }
        return null;
    }
}
