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
@Schema(description = "Psychological Type Request")
public class PsychologicalTypeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "First Name of the PsychologicalSpeciality.",
            example = "Estudante/Pessoa Juridica/Profissional Liberal", required = true)
    private String description;
}
