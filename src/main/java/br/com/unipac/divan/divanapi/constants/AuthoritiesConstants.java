package br.com.unipac.divan.divanapi.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthoritiesConstants {

    /**
     * The constant ANONYMOUS.
     */
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    /**
     * The constant ADMIN.
     */
    public static final String ADMIN = "ROLE_ADMIN";
    /**
     * The constant USER.
     */
    public static final String USER = "ROLE_USER";

    /**
     * The constant SYSTEM_EMAIL.
     */
    public static final String SYSTEM_EMAIL = "root@localhost";
    /**
     * The constant SYSTEM_NAME.
     */
    public static final String SYSTEM_NAME = "home-office-api";
    /**
     * The constant SYSTEM_ID.
     */
    public static final Long SYSTEM_ID = 1l;
}
