package br.com.unipac.divan.divanapi.api.dto.request.patient;

import br.com.unipac.divan.divanapi.constants.Constants;
import br.com.unipac.divan.divanapi.model.domain.Gender;
import br.com.unipac.divan.divanapi.model.domain.MaritalStatus;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Patient Request")
public class PatientRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "First Name of the Patient.",
            example = "Jessica Abigail", required = true)
    @NotNull
    private String firstName;

    @Schema(description = "Last Name of the Patient.",
            example = "Jessica Abigail", required = true)
    @NotNull
    private String lastName;

    @Schema(description = "Email address of the Patient.",
            example = "jessica@ngilang.com", required = true)
    @Email(message = "Email Address")
    @NotNull
    @Size(max = 45)
    private String email;

    @Schema(description = "Phone number of the Patient.",
            example = "(99) 9999-9999", required = true)
    @Pattern(regexp = "^\\(\\d{2,}\\) \\d{4,}\\-\\d{4}$", message = "Phone number")
    @NotNull
    @Size(max = 15)
    private String phone;

    @Schema(description = "Mobile Phone number of the Patient.",
            example = "(99) 9999-9999", required = true)
    @Pattern(regexp = "^\\(\\d{2,}\\) \\d{4,}\\-\\d{4}$", message = "Mobile Phone number")
    @NotNull
    @Size(max = 15)
    private String mobile;

    @Schema(description = "Address line 2 of the Patient.",
            example = "Condomínio Floriano Center - 615 Sala 908/909, Av. Floriano Peixoto, 605 - Centro, Uberlândia - MG", required = true)
    @Size(max = 255)
    @NotNull
    private String address;

    @Schema(description = "Patient is Active.",
            example = "true or false", required = true)
    @NotNull
    private boolean recurrent;

    @Schema(description = "Name of the Patient.",
            example = "Jessica Abigail Association", required = false, ref = "Association")
    @NotNull
    private Long patientTypeId;

    @Schema(description = "Name of the Patient.",
            example = "Jessica Abigail Association", required = false, ref = "Association")
    @NotNull
    private Long associationId;

    @Schema(description = "Gender type of the Psychological.",
            example = "MALE/FEMALE/OTHER", required = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Schema(description = "RG number of the Psychological.",
            example = "SP 9999999", required = false)
    @Pattern(regexp = "^(\\d{2}\\x2E\\d{3}\\x2E\\d{3}[-]\\d{1})$|^(\\d{2}\\x2E\\d{3}\\x2E\\d{3})$", message = "document id number")
    private String documentId; //RG

    @Schema(description = "crp number of the Psychological.",
            example = "SP", required = false)
    @Column(name = "document_district")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Mobile Phone number")
    private String documentDistrict;

    @Schema(description = "documentDispatchDate of the Psychological.",
            format = "ISO8601 date string",
            example = "13/09/2022", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DOCUMENT_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DOCUMENT_DATE)
    @Column(name = "document_dispatch_date")
    private LocalDate documentDispatchDate;

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$", message = "Mobile Phone number")
    private String socialId; //CPF

    @Schema(description = "socialIdDispatchDate of the Psychological.",
            format = "ISO8601 date string",
            example = "13/09/2022", required = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DOCUMENT_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DOCUMENT_DATE)
    private LocalDate socialIdDispatchDate; //CPF

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus; ////estado civil,

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    private String mothersName;
    //user_account
}
