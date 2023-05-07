package br.com.unipac.divan.divanapi.api.dto.response.authentication;

import br.com.unipac.divan.divanapi.model.entities.user.Profile;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * The type Login response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, of = {"userId", "token", "roles"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse implements Serializable {
    private static final long serialVersionUID = -8147520686839600954L;
    private Long id;
    private Long userId;
    private String name;
    private String email;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Set<Profile> roles;
}
