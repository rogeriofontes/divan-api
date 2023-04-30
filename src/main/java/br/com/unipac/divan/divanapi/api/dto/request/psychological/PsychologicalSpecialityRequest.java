package br.com.unipac.divan.divanapi.api.dto.request.psychological;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Psychological Speciality Request")
public class PsychologicalSpecialityRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "First Name of the PsychologicalSpeciality.",
            example = "hipocondríaco", required = true)
    private String description;
}
