package br.com.unipac.divan.divanapi.model.entities.patient;


import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Patient object")
public class PatientProblem extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the PatientProblem.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Schema(description = "First Name of the PatientProblem.",
            example = "Jessica Abigail", required = true)
    private String description;

    @Schema(description = "Name of the PatientProblem.",
            example = "Hipocondríaco", required = false, ref = "Patient")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Schema(description = "Name of the PatientProblem.",
            example = "Hipocondríaco", required = false, ref = "ProblemType")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "problem_type_id")
    private ProblemType problemType;

    @Schema(description = "Patient is Active.",
            example = "true or false", required = true)
    @NotNull
    private boolean evolved;

    public void update(Long id, PatientProblem patientProblem) {
        this.id = id;
        this.description = patientProblem.getDescription();
        this.patient = patientProblem.getPatient();
        this.problemType = patientProblem.getProblemType();
        this.evolved = patientProblem.isEvolved();
    }
}
