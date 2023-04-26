package br.com.unipac.divan.divanapi.model.entities.association.project;


import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientType;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_association")
public class Project extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    @Column(name = "short_description")
    private String shortDescription;
    private String description;

    @NotNull
    @Column(name = "start_date", nullable = false)
    @ApiModelProperty(notes = "Start Date")
    private LocalDateTime start_date;

    @NotNull
    @Column(name = "end_date", nullable = false)
    @ApiModelProperty(notes = "End Date")
    private LocalDateTime foundationDate;

    private String email;
    private String address;
    private String about;
    private boolean online;

    @ApiModelProperty(notes = "url")
    private String url;

    private boolean repeat;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "association_id")
    @ApiModelProperty(value = "Association")
    private Association association;

}
