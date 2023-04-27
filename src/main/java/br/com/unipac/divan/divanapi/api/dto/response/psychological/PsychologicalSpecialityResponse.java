package br.com.unipac.divan.divanapi.api.dto.response.psychological;


import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PsychologicalSpecialityResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
}
