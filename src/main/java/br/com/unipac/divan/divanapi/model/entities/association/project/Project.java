package br.com.unipac.divan.divanapi.model.entities.association.project;


import br.com.unipac.divan.divanapi.constants.Constants;
import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_project")
@Schema(description = "Project object")
public class Project extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the Project.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Schema(description = "Name of the Project.",
            example = "Jessica Abigail", required = true)
    @NotNull
    private String name;

    @Schema(description = "Notes about the Project.",
            example = "Meet her at Spring Boot Conference", required = false)
    @Column(name = "short_description", length = 1000)
    @NotNull
    private String shortDescription;

    @Schema(description = "Notes about the Project.",
            example = "Meet her at Spring Boot Conference", required = false)
    @Column(length = 5000)
    private String description;

    @Schema(title = "The publication date of this Project.",
            format = "ISO8601 date string",
            description = "Notes about the contact.",
            example = "13/09/2022 14:31:34", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DATE)
    @Column(name = "start_date", nullable = false)
    @NotNull
    private LocalDateTime startDate;

    @Schema(title = "The publication date of this Project.",
            format = "ISO8601 date string",
            description = "Notes about the contact.",
            example = "13/09/2022 14:31:34", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DATE)
    @Column(name = "end_date", nullable = false)
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
    @Column(length = 4000)
    private String about;
    private boolean online;

    @Schema(description = "Notes about the Project.",
            example = "Meet her at Spring Boot Conference", required = false)
    @Column(length = 255)
    private String url;

    @Schema(description = "Project is Active.",
            example = "true or false", required = true)
    @NotNull
    private boolean repeat;

    @Schema(description = "Name of the Project.",
            example = "Jessica Abigail Project", required = false, ref = "Association")
    @ManyToOne
    @JoinColumn(name = "association_id")
    @NotNull
    private Association association;

    public void update(Long id, Project project) {
        this.id = id;
        this.name = project.getName();
        this.shortDescription = project.getShortDescription();
        this.description = project.getDescription();
        this.startDate = project.getStartDate();
        this.foundationDate = project.getFoundationDate();
        this.email = project.getEmail();
        this.address = project.getAddress();
        this.about = project.getAbout();
        this.online = project.isOnline();
        this.url = project.getUrl();
        this.repeat = project.isRepeat();
        this.association = project.getAssociation();
    }
}
