package br.com.unipac.divan.divanapi.api.dto.request.patient;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PatientProblemRequest  implements Serializable  {

    private static final long serialVersionUID = 1L;

    private String description;
    private Long patientId;
    private Long problemTypeId;
    private boolean evolved;
}
