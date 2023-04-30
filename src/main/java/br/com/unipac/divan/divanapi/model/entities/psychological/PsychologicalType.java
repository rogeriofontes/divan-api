package br.com.unipac.divan.divanapi.model.entities.psychological;

import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_psychological_type")
@Schema(description = "Patient object")
public class PsychologicalType extends AuditModel {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the PsychologicalSpeciality.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Schema(description = "First Name of the PsychologicalSpeciality.",
            example = "Estudante/Pessoa Juridica/Profissional Liberal", required = true)
    private String description;

    public void update(Long id, PsychologicalType psychologicalType) {
        this.id = id;
        this.description = psychologicalType.getDescription();
    }
}
