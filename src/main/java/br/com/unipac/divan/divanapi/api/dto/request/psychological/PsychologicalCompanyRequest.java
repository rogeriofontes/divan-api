package br.com.unipac.divan.divanapi.api.dto.request.psychological;

import br.com.unipac.divan.divanapi.model.entities.psychological.Psychological;
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
@Schema(description = "Psychological Company Request")
public class PsychologicalCompanyRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Company Name of the PsychologicalSpeciality.",
            example = "Estudante/Pessoa Juridica/Profissional Liberal", required = true)
    private String name;

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    private String companyId; //CNPJ

    @Schema(description = "Type of the Psychological.",
            example = "Jessica Abigail Association", required = false, ref = "psychologicalSpeciality")
    private Psychological psychological;
}
