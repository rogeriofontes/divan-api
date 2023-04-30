package br.com.unipac.divan.divanapi.model.entities.user;

import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Patient object")
public class User extends AuditModel  { //implements UserDetails
    private static final long serialVersionUID = 3305563921155141378L;

    @Schema(description = "Unique identifier of the Patient.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_user_profile", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private Set<Profile> profiles;

    @Schema(description = "Last Access address of the User.",
            example = "jessica@ngilang.com", required = true)
    @Email(message = "Email Address")
    @NotNull
    @Column(name = "last_access", nullable = false)
    private LocalDateTime lastAccess;

    @Builder
    public User(Long id, String name, String email, String password, Set<Profile> profiles, LocalDateTime lastAccess) {
        this.setId(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.profiles = profiles;
        this.lastAccess = lastAccess;
    }

    /**
     * Update.
     *
     * @param id   the id
     * @param user the user
     */
    public void update(Long id, User user) {
        this .setId(id);
        this.setName(user.getName());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
    }
}
