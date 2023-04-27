package br.com.unipac.divan.divanapi.api.dto.request.psychological;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PsychologicalSpecialityRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String description;
}
