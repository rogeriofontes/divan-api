package br.com.unipac.divan.divanapi.api.dto.request.patient;


import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_patient_type")
public class PatientTypeRequest extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String description;

    public void update(Long id, PatientTypeRequest patientType) {
        this.id = id;
        this.description = patientType.getDescription();
    }
}
