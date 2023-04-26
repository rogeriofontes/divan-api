package br.com.unipac.divan.divanapi.model.entities.psychological;

import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientType;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_patient")
public class Psychological extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "fist_name")
    private String firtName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String phone;

    private String mobile;

    private String address;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "association_id")
    @ApiModelProperty(value = "Patient Type")
    private Association association;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "psychological_speciality_id")
    @ApiModelProperty(value = "Psychological Speciality")
    private PsychologicalSpeciality psychologicalSpeciality;

    public void update(Long id, Psychological psychological) {
        this.id = id;
        this.firtName = psychological.getFirtName();
        this.lastName = psychological.getLastName();
        this.email = psychological.getEmail();
        this.phone = psychological.getPhone();
        this.mobile = psychological.getMobile();
        this.address = psychological.getAddress();
        this.association = psychological.getAssociation();
        this.psychologicalSpeciality = psychological.getPsychologicalSpeciality();
    }
}
