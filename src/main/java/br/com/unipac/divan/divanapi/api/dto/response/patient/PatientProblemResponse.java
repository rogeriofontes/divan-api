package br.com.unipac.divan.divanapi.api.dto.response.patient;

import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import br.com.unipac.divan.divanapi.model.entities.patient.ProblemType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Patient Problem Response")
public class PatientProblemResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the PatientProblem.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "First Name of the PatientProblem.",
            example = "Jessica Abigail", required = true)
    private String description;

    @Schema(description = "Name of the PatientProblem.",
            example = "Hipocondríaco", required = false, ref = "Patient")
    @NotNull
    private Patient patient;

    @Schema(description = "Name of the PatientProblem.",
            example = "Hipocondríaco", required = false, ref = "ProblemType")
    @NotNull
    private ProblemType problemType;

    @Schema(description = "Patient is Active.",
            example = "true or false", required = true)
    @NotNull
    private boolean evolved;
}
