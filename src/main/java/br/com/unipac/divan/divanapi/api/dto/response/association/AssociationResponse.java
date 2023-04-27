package br.com.unipac.divan.divanapi.api.dto.response.association;


import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientTypeResponse;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class AssociationResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String address;
    private String about;
    private boolean recurrent;
    private LocalDateTime foundationDate;
}
