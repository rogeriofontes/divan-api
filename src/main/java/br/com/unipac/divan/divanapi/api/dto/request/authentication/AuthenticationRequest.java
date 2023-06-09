package br.com.unipac.divan.divanapi.api.dto.request.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * The type Login request.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Patient Request")
public class AuthenticationRequest implements Serializable {
    private static final long serialVersionUID = 4023504641313778939L;
    private String name;
    private String email;
    private String password;
}
