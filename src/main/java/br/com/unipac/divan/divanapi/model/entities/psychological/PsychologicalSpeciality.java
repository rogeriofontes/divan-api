package br.com.unipac.divan.divanapi.model.entities.psychological;


import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_psychological_speciality")
public class PsychologicalSpeciality extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String description;

    public void update(Long id, PsychologicalSpeciality psychologicalSpeciality) {
        this.id = id;
        this.description = psychologicalSpeciality.getDescription();
    }
}
