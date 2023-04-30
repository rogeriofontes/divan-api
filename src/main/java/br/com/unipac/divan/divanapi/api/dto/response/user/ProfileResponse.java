package br.com.unipac.divan.divanapi.api.dto.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

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
@Schema(description = "Profile Response")
public class ProfileResponse implements Serializable {
    private static final long serialVersionUID = -1483351163120427247L;

    @Schema(description = "Unique identifier of the Patient.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "Role of the Profile.",
            example = "basic_user", required = true)
    private String role;
}
