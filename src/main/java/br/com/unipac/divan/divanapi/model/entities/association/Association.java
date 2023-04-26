package br.com.unipac.divan.divanapi.model.entities.association;


import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_association")
public class Association extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    private String email;
    private String address;
    private String about;
    private boolean recurrent;

    @NotNull
    @Column(name = "foundation_date", nullable = false)
    @ApiModelProperty(notes = "Foundation Date")
    private LocalDateTime foundationDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "patient_type_id")
    @ApiModelProperty(value = "Patient Type")
    private PatientType patientType;

    public void update(Long id, Association association) {
        this.id = id;
        this.name = association.getName();
        this.email = association.getEmail();
        this.address = association.getAddress();
        this.about = association.getAbout();
        this.recurrent = association.isRecurrent();
        this.foundationDate = association.getFoundationDate();
        this.patientType = association.getPatientType();
    }
    //user_account
}
