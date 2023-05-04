package br.com.unipac.divan.divanapi.api.dto.request.login;

import lombok.*;

import java.io.Serializable;

/**
 * The type Login request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, of = {"email", "password"})
public class AuthenticationRequest implements Serializable {
    private static final long serialVersionUID = 4023504641313778939L;
    private Long id;
    private String email;
    private String password;
}
