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
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

/**
 * The type User.
 *
 * @author Rogério Fontes
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_user")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonTypeName(value = "tb_user")
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Patient object")
public class User extends AuditModel implements UserDetails { //implements UserDetails
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
    @Email(message = "Email Address")
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

    @NotNull
    @Column(name = "last_access", nullable = false)
    private LocalDateTime lastAccess = LocalDateTime.now();

    /**
     * Update.
     *
     * @param id   the id
     * @param user the user
     */
    public void update(Long id, User user) {
        this.setId(id);
        this.setName(user.getName());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();

        profiles.forEach(profile -> {
            authorities.add(new SimpleGrantedAuthority(profile.getRole().toString()));
        });

        List<GrantedAuthority> temp = new ArrayList<>(authorities.size());
        temp.addAll(authorities);

        return temp;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
