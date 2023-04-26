package br.com.unipac.divan.divanapi.api.dto.request.user;

import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Profile", description = "Model")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileRequest extends AuditModel {
    private static final long serialVersionUID = -1483351163120427247L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ApiModelProperty(notes = "role")
    private String role;

    /**
     * Update.
     *
     * @param id      the id
     * @param profile the profile
     */
    public void update(Long id, ProfileRequest profile) {
        this.setId(id);
        this.setRole(profile.getRole());
    }

}
