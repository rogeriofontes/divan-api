package br.com.unipac.divan.divanapi.api.dto.response.psychological;


import br.com.unipac.divan.divanapi.model.entities.psychological.Psychological;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Psychological Company Response")
public class PsychologicalCompanyResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the PsychologicalSpeciality.",
            example = "1", required = true)
    private Long id;

    @Schema(description = "Company Name of the PsychologicalSpeciality.",
            example = "Estudante/Pessoa Juridica/Profissional Liberal", required = true)
    private String name;

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    private String companyId; //CNPJ

    @Schema(description = "Type of the Psychological.",
            example = "Jessica Abigail Association", required = false, ref = "psychologicalSpeciality")
    @NotNull
    private Psychological psychological;
}
