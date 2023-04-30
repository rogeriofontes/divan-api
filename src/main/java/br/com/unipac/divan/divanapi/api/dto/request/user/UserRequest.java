package br.com.unipac.divan.divanapi.api.dto.request.user;

import br.com.unipac.divan.divanapi.model.entities.user.Profile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * The type User.
 *
 * @author Rogério Fontes
 */
@Builder
@Data
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Schema(description = "User Request")
public class UserRequest implements Serializable {
    private static final long serialVersionUID = 3305563921155141378L;

    @Schema(description = "Name of the User.",
            example = "Jessica Abigail", required = true)
    @NotNull(message = "O campo \"name\" é obrigatório")
    private String name;

    @Schema(description = "Email address of the User.",
            example = "jessica@ngilang.com", required = true)
    @NotNull(message = "O campo \"email\" é obrigatório")
    private String email;

    @Schema(description = "Password address of the User.",
            example = "*******", required = true)
    @NotNull
    private String password;

    @Schema(description = "profiles of the User.",
            example = "Jessica Abigail User", required = false, ref = "Association")
    private Set<Profile> profiles;

    @Schema(description = "Last Access address of the User.",
            example = "jessica@ngilang.com", required = true)
    @Email(message = "Email Address")
    @NotNull
    private LocalDateTime lastAccess;

    @Builder
    public UserRequest(String name, String email, String password, Set<Profile> profiles, LocalDateTime lastAccess) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profiles = profiles;
        this.lastAccess = lastAccess;
    }
}
