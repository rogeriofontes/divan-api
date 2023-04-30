package br.com.unipac.divan.divanapi.api.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Profile Request")
public class ProfileRequest  implements Serializable {
    private static final long serialVersionUID = -1483351163120427247L;

    @Schema(description = "Role of the Profile.",
            example = "basic_user", required = true)
    private String role;

}
