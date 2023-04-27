package br.com.unipac.divan.divanapi.api.dto.request.user;

import io.swagger.annotations.ApiModelProperty;
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
public class ProfileRequest  implements Serializable {
    private static final long serialVersionUID = -1483351163120427247L;

    @ApiModelProperty(notes = "role")
    private String role;

}
