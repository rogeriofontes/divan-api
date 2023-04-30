package br.com.unipac.divan.divanapi.api.dto.request.association;

import br.com.unipac.divan.divanapi.constants.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Association Request")
public class AssociationRequest  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Name of the Association.",
            example = "Jessica Abigail", required = true)
    @NotNull
    private String name;

    @Schema(description = "Phone number of the Association.",
            example = "(99) 9999-9999", required = true)
    //@Pattern(regexp ="^\\+?[0-9. ()-]{7,25}$", message = "Phone number")
    @Pattern(regexp = "^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$", message = "Phone number")
    @NotNull
    @Size(max = 15)
    private String phone;

    @Schema(description = "Email address of the Association.",
            example = "jessica@ngilang.com", required = true)
    @Email(message = "Email Address")
    @NotNull
    @Size(max = 45)
    private String email;

    @Schema(description = "Address line 2 of the Association.",
            example = "Condomínio Floriano Center - 615 Sala 908/909, Av. Floriano Peixoto, 605 - Centro, Uberlândia - MG", required = true)
    @Size(max = 255)
    @NotNull
    private String address;

    @Schema(description = "Postal code of the Association.",
            example = "38400-102", required = true)
    @Size(max = 20)
    @NotNull
    private String postalCode;

    @Schema(description = "Notes about the Association.",
            example = "Meet her at Spring Boot Conference", required = false)
    private String about;

    @Schema(description = "Association is Active.",
            example = "true or false", required = true)
    @NotNull
    private boolean active;

    @Schema(title = "The publication date of this post.",
            format = "ISO8601 date string",
            description = "Notes about the contact.",
            example = "13/09/2022 14:31:34", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DATE)
    @NotNull
    private LocalDateTime foundationDate;
}
