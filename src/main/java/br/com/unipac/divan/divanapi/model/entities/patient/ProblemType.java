package br.com.unipac.divan.divanapi.model.entities.patient;


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
@Table(name = "tb_problem_type")
@Schema(description = "Patient object")
public class ProblemType extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the ProblemType.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Schema(description = "First Name of the ProblemType.",
            example = "hipocondríaco", required = true)
    private String description;

    public void update(Long id, ProblemType problemType) {
        this.id = id;
        this.description = problemType.getDescription();
    }
}
