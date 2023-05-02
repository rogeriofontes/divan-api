package br.com.unipac.divan.divanapi.model.entities.psychological;

import br.com.unipac.divan.divanapi.constants.Constants;
import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import br.com.unipac.divan.divanapi.model.domain.Gender;
import br.com.unipac.divan.divanapi.model.domain.MaritalStatus;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_psychological")
@Schema(description = "Patient object")
public class Psychological extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the Psychological.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Schema(description = "First Name of the Psychological.",
            example = "Jessica Abigail", required = true)
    @Column(name = "fist_name")
    private String firstName;

    @Schema(description = "Last Name of the Psychological.",
            example = "Jessica Abigail", required = true)
    @Column(name = "last_name")
    private String lastName;

    @Schema(description = "Email address of the Psychological.",
            example = "jessica@ngilang.com", required = true)
    @Email(message = "Email Address")
    @NotNull
    @Size(max = 45)
    private String email;

    @Schema(description = "Phone number of the Patient.",
            example = "(99) 9999-9999", required = true)
    @Pattern(regexp = "^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$", message = "Phone number")
    @NotNull
    @Size(max = 15)
    private String phone;

    @Schema(description = "Mobile Phone number of the Patient.",
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
    @ManyToOne
    @JoinColumn(name = "association_id")
     private Association association;

    @Schema(description = "Name of the Psychological.",
            example = "Jessica Abigail Association", required = false, ref = "psychologicalSpeciality")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "psychological_speciality_id")
    private PsychologicalSpeciality psychologicalSpeciality;

    @Schema(description = "Type of the Psychological.",
            example = "Jessica Abigail Association", required = false, ref = "psychologicalSpeciality")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "psychological_type_id")
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
    @Column(name = "document_id")
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
    @Column(name = "social_id")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$", message = "CPF number")
    private String socialId; //CPF

    @Schema(description = "socialIdDispatchDate of the Psychological.",
            format = "ISO8601 date string",
            example = "13/09/2022", required = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DOCUMENT_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DOCUMENT_DATE)
    @Column(name = "social_id_dispatch_date")
    private String socialIdDispatchDate; //CPF

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus; ////estado civil,

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    @Column(name = "mothers_name")
    private String mothersName;

    public void update(Long id, Psychological psychological) {
        this.id = id;
        this.firstName = psychological.getFirstName();
        this.lastName = psychological.getLastName();
        this.email = psychological.getEmail();
        this.phone = psychological.getPhone();
        this.mobile = psychological.getMobile();
        this.address = psychological.getAddress();
        this.association = psychological.getAssociation();
        this.psychologicalSpeciality = psychological.getPsychologicalSpeciality();
        this.psychologicalType = psychological.getPsychologicalType();
        this.student = psychological.isStudent();
        this.crp = psychological.getCrp();
        this.gender = psychological.getGender();
        this.documentId = psychological.getDocumentId();
        this.documentDistrict = psychological.getDocumentDistrict();
        this.documentDispatchDate = psychological.getDocumentDispatchDate();
        this.socialId = psychological.getSocialId();
        this.socialIdDispatchDate = psychological.getSocialIdDispatchDate();
        this.maritalStatus = psychological.getMaritalStatus();
        this.mothersName = psychological.getMothersName();
    }
}
