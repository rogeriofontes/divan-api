package br.com.unipac.divan.divanapi.api.dto.response.patient;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ProblemTypeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;

}
