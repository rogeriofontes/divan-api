package br.com.unipac.divan.divanapi.model.domain;

import lombok.*;

import java.io.Serializable;

/**
 * The type Account credentials.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, of = {"username", "password"})
public class AccountCredentials implements Serializable {
    /**
     * The Serial version uid.
     */
    static final long serialVersionUID = -3147203146262241574L;

    private String username;

    private String password;
}
