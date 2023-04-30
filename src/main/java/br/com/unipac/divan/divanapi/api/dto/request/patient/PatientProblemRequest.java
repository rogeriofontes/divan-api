package br.com.unipac.divan.divanapi.api.dto.request.patient;

import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import br.com.unipac.divan.divanapi.model.entities.patient.ProblemType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Patient Problem Request")
public class PatientProblemRequest  implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Schema(description = "First Name of the PatientProblem.",
            example = "Jessica Abigail", required = true)
    private String description;

    @Schema(description = "Name of the PatientProblem.",
            example = "Hipocondríaco", required = false, ref = "Patient")
    @NotNull
    private Long patientId;

    @Schema(description = "Name of the PatientProblem.",
            example = "Hipocondríaco", required = false, ref = "ProblemType")
    @NotNull
    private Long problemTypeId;

    @Schema(description = "Patient is Active.",
            example = "true or false", required = true)
    @NotNull
    private boolean evolved;
}
