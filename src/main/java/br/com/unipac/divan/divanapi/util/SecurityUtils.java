package br.com.unipac.divan.divanapi.util;

import br.com.unipac.divan.divanapi.constants.AuthoritiesConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * The type Security utils.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

    /**
     * Is current user admin boolean.
     *
     * @return the boolean
     */
    public static boolean isCurrentUserAdmin() {
        String currentLogin = getCurrentLogin();
        return AuthoritiesConstants.SYSTEM_EMAIL.equals(currentLogin);
    }

    /**
     * Get the login of the current user.
     *
     * @return the current login
     */
    public static String getCurrentLogin() {
        Authentication authentication = getAuthentication();
        UserDetails springSecurityUser;
        String userName = null;

        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }

        return userName;
    }

    /**
     * Gets authentication.
     *
     * @return the authentication
     */
    public static Authentication getAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication();
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        final Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();

        if (authorities != null) {
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Authenticate as admin.
     */
    public static void authenticateAsAdmin() {
        authenticateAs("ROLE_ADMIN", AuthoritiesConstants.SYSTEM_EMAIL, "admin");
    }

    /**
     * Authenticate as.
     *
     * @param role     the role
     * @param email    the email
     * @param password the password
     */
    public static void authenticateAs(String role, String email, String password) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(simpleGrantedAuthority);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Logoff.
     */
    public static void logoff() {
        SecurityContextHolder.clearContext();
    }
}
