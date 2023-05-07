package br.com.unipac.divan.divanapi.api.dto.response.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * The type Login response.
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, of = {"id","userId", "name", "email"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class SignupResponse implements Serializable {
    private static final long serialVersionUID = -8147520686839600954L;
    private Long id;
    private Long userId;
    private String name;
    private String email;
}
