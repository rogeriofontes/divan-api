package br.com.unipac.divan.divanapi.api.dto.request.psychological;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$", message = "company Id number")
    private String companyId; //CNPJ

    @Schema(description = "Type of the Psychological.",
            example = "Jessica Abigail Association", required = false, ref = "Psychological")
    private Long psychologicalId;
}
