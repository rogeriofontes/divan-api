package br.com.unipac.divan.divanapi.api.dto.response.psychological;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Psychological Speciality Response")
public class PsychologicalSpecialityResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the Psychological Speciality.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "First Name of the PsychologicalSpeciality.",
            example = "hipocondríaco", required = true)
    private String description;
}
