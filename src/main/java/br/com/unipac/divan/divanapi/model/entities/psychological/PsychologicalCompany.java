package br.com.unipac.divan.divanapi.model.entities.psychological;

import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_psychological_company")
@Schema(description = "Patient object")
public class PsychologicalCompany extends AuditModel {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the PsychologicalSpeciality.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Schema(description = "Company Name of the PsychologicalSpeciality.",
            example = "Estudante/Pessoa Juridica/Profissional Liberal", required = true)
    private String name;

    @Schema(description = "crp number of the Psychological.",
            example = "Jessica Abigail", required = false)
    @Column(name = "company_id")
    //@Pattern(regexp = "^\\([1-9]{2}\\) 9[7-9]{1}[0-9]{3}\\-[0-9]{4}$", message = "Mobile Phone number")
    private String companyId; //CNPJ

    @Schema(description = "Type of the Psychological.",
            example = "Jessica Abigail Association", required = false, ref = "psychologicalSpeciality")
    @NotNull
    @OneToOne
    @JoinColumn(name = "psychological_id")
    private Psychological psychological;

    public void update(Long id, PsychologicalCompany psychologicalCompany) {
        this.id = id;
        this.name = psychologicalCompany.getName();
        this.companyId = psychologicalCompany.getCompanyId();
        this.psychological = psychologicalCompany.getPsychological();
    }
}
