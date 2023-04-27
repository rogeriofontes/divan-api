package br.com.unipac.divan.divanapi.api.dto.response.user;

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
public class ProfileResponse implements Serializable {
    private static final long serialVersionUID = -1483351163120427247L;

    private Long id;
    @ApiModelProperty(notes = "role")
    private String role;
}
