package br.com.unipac.divan.divanapi.api.dto.request.authentication;

import lombok.*;

import java.io.Serializable;

/**
 * The type Login request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, of = {"nome", "email", "password"})
public class SignupRequest implements Serializable {
    private static final long serialVersionUID = 4023504641313778939L;
    private String name;
    private String email;
    private String password;
}
