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
@Entity
@Table(name = "tb_user")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonTypeName(value = "tb_user")
@ApiModel(value = "User", description = "Model")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse extends AuditModel  { //implements UserDetails
    private static final long serialVersionUID = 3305563921155141378L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_user_profile", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "profile_id"))
    @ApiModelProperty(notes = "profiles")
    private Set<ProfileResponse> profiles;

    @NotNull
    @Column(name = "last_access", nullable = false)
    private LocalDateTime lastAccess;

    @NotNull
    @Column(name = "social_id", nullable = false)
    private Long socialId;

    @NotNull
    @Column(name = "social_type", nullable = false)
    private String socialType;

    @NotNull
    @Column(name = "register_number", nullable = false)
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

    /**
     * Update.
     *
     * @param id   the id
     * @param user the user
     */
    public void update(Long id, UserResponse user) {
        this .setId(id);
        this.setName(user.getName());
        this.setEmail(user.getEmail());
        this.setSocialId(user.getSocialId());
        this.setPassword(user.getPassword());
    }
}
