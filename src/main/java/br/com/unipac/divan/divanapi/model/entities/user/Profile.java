package br.com.unipac.divan.divanapi.model.entities.user;

import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The type Profile.
 *
 * @author Rogério Fontes
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "tb_profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonTypeName(value = "tb_profile")
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Patient object")
public class Profile extends AuditModel {
    private static final long serialVersionUID = -1483351163120427247L;

    @Schema(description = "Unique identifier of the Patient.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Schema(description = "Role of the Profile.",
            example = "basic_user", required = true)
    private String role;

    /**
     * Update.
     *
     * @param id      the id
     * @param profile the profile
     */
    public void update(Long id, Profile profile) {
        this.setId(id);
        this.setRole(profile.getRole());
    }

}
