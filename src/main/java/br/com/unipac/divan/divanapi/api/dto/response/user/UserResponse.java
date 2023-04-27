package br.com.unipac.divan.divanapi.api.dto.response.user;

import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 3305563921155141378L;

    private Long id;
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
    private Set<ProfileResponse> profiles;
    @NotNull
    private LocalDateTime lastAccess;
    @NotNull
    private Long socialId;
    @NotNull
    private String socialType;
    @NotNull
    private String registerNumber;

    @Builder
    public UserResponse(Long id, String name, String email, String password, Set<ProfileResponse> profiles, LocalDateTime lastAccess, Long socialId, String socialType, String registerNumber) {
        this.setId(id);
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
