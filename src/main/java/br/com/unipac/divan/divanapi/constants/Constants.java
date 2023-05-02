package br.com.unipac.divan.divanapi.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String BRAZILIAN_DATE = "dd/MM/yyyy HH:mm:ss";


    public static final String BRAZILIAN_DOCUMENT_DATE = "dd/MM/yyyy";

    /**
     * The constant RESPONSE_UNSUCCESS.
     */
    public static final String RESPONSE_UNSUCCESS = "unsuccess";

    /**
     * The constant RESPONSE_SUCCESS.
     */
    public static final String RESPONSE_SUCCESS = "success";

    /**
     * The constant CURRENT_USER.
     */
    public static final String CURRENT_USER = "root@localhost";
}
