package br.com.unipac.divan.divanapi.api.dto.request.patient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Patient Type Request")
public class PatientTypeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "First Name of the PatientType.",
            example = "hipocondríaco", required = true)
    private String description;

}
