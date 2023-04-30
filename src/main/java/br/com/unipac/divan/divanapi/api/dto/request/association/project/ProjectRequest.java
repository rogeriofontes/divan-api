package br.com.unipac.divan.divanapi.api.dto.request.association.project;

import br.com.unipac.divan.divanapi.constants.Constants;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
@Schema(description = "Project Request")
public class ProjectRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Name of the Project.",
            example = "Jessica Abigail", required = true)
    @NotNull
    private String name;

    @Schema(description = "Notes about the Project.",
            example = "Meet her at Spring Boot Conference", required = false)
    @NotNull
    private String shortDescription;

    @Schema(description = "Notes about the Project.",
            example = "Meet her at Spring Boot Conference", required = false)
    private String description;

    @Schema(title = "The publication date of this Project.",
            format = "ISO8601 date string",
            description = "Notes about the contact.",
            example = "13/09/2022 14:31:34", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DATE)
    @NotNull
    private LocalDateTime startDate;

    @Schema(title = "The publication date of this Project.",
            format = "ISO8601 date string",
            description = "Notes about the contact.",
            example = "13/09/2022 14:31:34", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DATE)
    @NotNull
    private LocalDateTime foundationDate;

    @Schema(description = "Email address of the Project.",
            example = "jessica@ngilang.com", required = true)
    @Email(message = "Email Address")
    @NotNull
    @Size(max = 45)
    private String email;

    @Schema(description = "Address line 2 of the Project.",
            example = "Condomínio Floriano Center - 615 Sala 908/909, Av. Floriano Peixoto, 605 - Centro, Uberlândia - MG", required = true)
    @Size(max = 255)
    @NotNull
    private String address;

    @Schema(description = "Notes about the Project.",
            example = "Meet her at Spring Boot Conference", required = false)
    private String about;

   private boolean online;

    @Schema(description = "Notes about the Project.",
            example = "Meet her at Spring Boot Conference", required = false)
    private String url;

    @Schema(description = "Project is Active.",
            example = "true or false", required = true)
    @NotNull
    private boolean repeat;

    @Schema(description = "Name of the Project.",
            example = "Jessica Abigail Project", required = false, ref = "Association")
    @NotNull
    private Long associationId;

}
