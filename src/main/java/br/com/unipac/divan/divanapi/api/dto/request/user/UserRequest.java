package br.com.unipac.divan.divanapi.api.dto.request.user;

import io.swagger.annotations.ApiModelProperty;
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
public class UserRequest implements Serializable {
    private static final long serialVersionUID = 3305563921155141378L;

    @NotNull(message = "O campo \"name\" é obrigatório")
    @ApiModelProperty(notes = "name")
    private String name;

    @NotNull(message = "O campo \"email\" é obrigatório")
    @ApiModelProperty(notes = "email")
    private String email;

    @NotNull
    @ApiModelProperty(notes = "password")
    private String password;

    @ApiModelProperty(notes = "profiles")
    private Set<ProfileRequest> profiles;

    @NotNull
    private LocalDateTime lastAccess;

    @NotNull
    private Long socialId;

    @NotNull
    private String socialType;

    @NotNull
    private String registerNumber;

    @Builder
    public UserRequest(String name, String email, String password, Set<ProfileRequest> profiles, LocalDateTime lastAccess, Long socialId, String socialType, String registerNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profiles = profiles;
        this.lastAccess = lastAccess;
        this.socialId = socialId;
        this.socialType = socialType;
        this.registerNumber = registerNumber;
    }
}
