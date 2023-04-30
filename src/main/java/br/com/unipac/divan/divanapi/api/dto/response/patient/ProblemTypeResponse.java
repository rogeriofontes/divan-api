package br.com.unipac.divan.divanapi.api.dto.response.patient;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
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
@Schema(description = "Problem Type Response")
public class ProblemTypeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the ProblemType.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "First Name of the ProblemType.",
            example = "hipocondríaco", required = true)
    private String description;

}
