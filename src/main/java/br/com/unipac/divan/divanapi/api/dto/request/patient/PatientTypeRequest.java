package br.com.unipac.divan.divanapi.api.dto.request.patient;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PatientTypeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String description;

}
