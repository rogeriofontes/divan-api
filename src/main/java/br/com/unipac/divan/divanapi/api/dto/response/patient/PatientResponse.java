package br.com.unipac.divan.divanapi.api.dto.response.patient;


import br.com.unipac.divan.divanapi.model.domain.AuditModel;
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
public class PatientResponse extends AuditModel {

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

    private boolean recurrent;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "patient_type_id")
    @ApiModelProperty(value = "Patient Type")
    private PatientTypeResponse patientType;

    public void update(Long id, PatientResponse patient) {
        this.id = id;
        this.firtName = patient.getFirtName();
        this.lastName = patient.getLastName();
        this.email = patient.getEmail();
        this.phone = patient.getPhone();
        this.mobile = patient.getMobile();
        this.address = patient.getAddress();
        this.recurrent = patient.isRecurrent();
        this.patientType = patient.getPatientType();
    }

    //user_account
}
