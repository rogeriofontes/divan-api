package br.com.unipac.divan.divanapi.api.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Schema(description = "Profile Request")
public class TokenRefreshRequest implements Serializable {

    @Schema(description = "refreshToken of the login.",
            example = "Jessica Abigail", required = true)
    @NotBlank
    private String refreshToken;
}
