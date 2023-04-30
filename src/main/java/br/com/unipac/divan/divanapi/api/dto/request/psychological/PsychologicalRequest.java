package br.com.unipac.divan.divanapi.api.dto.request.psychological;

import br.com.unipac.divan.divanapi.model.domain.Gender;
import br.com.unipac.divan.divanapi.model.domain.MaritalStatus;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalSpeciality;
import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Psychological Request")
public class PsychologicalRequest  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "First Name of the Psychological.",
            example = "Jessica Abigail", required = true)
    private String firstName;

    @Schema(description = "Last Name of the Psychological.",
            example = "Jessica Abigail", required = true)
    private String lastName;

    @Schema(description = "Email address of the Psychological.",
            example = "jessica@ngilang.com", required = true)
    @Email(message = "Email Address")
    @NotNull
    @Size(max = 45)
    private String email;

    @Schema(description = "Phone number of the Psychological.",
            example = "(99) 9999-9999", required = true)
    @Pattern(regexp = "^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$", message = "Phone number")
    @NotNull
    @Size(max = 15)
    private String phone;

    @Schema(description = "Mobile Phone number of the Psychological.",
            example = "(99) 9999-9999", required = true)
    @Pattern(regexp = "^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$", message = "Mobile Phone number")
    @NotNull
    @Size(max = 15)
    private String mobile;

    @Schema(description = "Address line 2 of the Psychological.",
            example = "Condomínio Floriano Center - 615 Sala 908/909, Av. Floriano Peixoto, 605 - Centro, Uberlândia - MG", required = true)
    @Size(max = 255)
    @NotNull
    private String address;

    @Schema(description = "Name of the Psychological.",
            example = "Jessica Abigail Association", required = false, ref = "Association")
    @NotNull
    private Long associationId;

    @Schema(description = "Name of the Psychological.",
            example = "Jessica Abigail Association", required = false, ref = "psychologicalSpeciality")
    @NotNull
    private Long psychologicalSpecialityId;

    @Schema(description = "Type of the Psychological.",
            example = "Jessica Abigail Association", required = false, ref = "psychologicalSpeciality")
    @NotNull
    private PsychologicalType psychologicalType;

    @Schema(description = "Psychological it's student.",
            example = "true or false", required = true)
    @NotNull
    private boolean student;

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    private String crp;

    @Schema(description = "Gender type of the Psychological.",
            example = "MALE/FEMALE/OTHER", required = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Schema(description = "RG number of the Psychological.",
            example = "SP 9999999", required = false)
    //@Pattern(regexp = "^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$", message = "Mobile Phone number")
    private String documentId; //RG

    @Schema(description = "crp number of the Psychological.",
            example = "SP", required = false)
    //@Pattern(regexp = "^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$", message = "Mobile Phone number")
    private String documentDistrict;

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
   private LocalDate documentDispatchDate;

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    //@Pattern(regexp = "^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$", message = "Mobile Phone number")
    private String socialId; //CPF

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    private String socialIdDispatchDate; //CPF

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus; ////estado civil,

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    private String mothersName;

}
