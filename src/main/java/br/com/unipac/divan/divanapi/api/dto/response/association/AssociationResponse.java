package br.com.unipac.divan.divanapi.api.dto.response.association;


import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientTypeResponse;
import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class AssociationResponse extends AuditModel {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String address;
    private String about;
    private boolean recurrent;
    private LocalDateTime foundationDate;
    private PatientTypeResponse patientType;
}
