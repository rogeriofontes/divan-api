package br.com.unipac.divan.divanapi.api.dto.response.login;

import br.com.unipac.divan.divanapi.model.entities.user.Profile;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * The type Login response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, of = {"userId", "token", "roles"})
public class LoginResponse implements Serializable {
    private static final long serialVersionUID = -8147520686839600954L;
    private Long id;
    private Long userId;
    private String token;
    private Set<Profile> roles;
}
