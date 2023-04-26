package br.com.unipac.divan.divanapi.model.entities.patient;


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
@Table(name = "tb_patient_problem")
public class PatientProblem extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "patient_id")
    @ApiModelProperty(value = "Patient")
    private Patient patient;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "problem_type_id")
    @ApiModelProperty(value = "Problem Type")
    private ProblemType problemType;

    private boolean evolved;

    public void update(Long id, PatientProblem patientProblem) {
        this.id = id;
        this.description = patientProblem.getDescription();
        this.patient = patientProblem.getPatient();
        this.problemType = patientProblem.getProblemType();
        this.evolved = patientProblem.isEvolved();
    }
}
