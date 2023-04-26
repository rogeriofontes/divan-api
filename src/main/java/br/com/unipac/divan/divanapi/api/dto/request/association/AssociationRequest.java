package br.com.unipac.divan.divanapi.api.dto.request.association;


import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientTypeRequest;
import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class AssociationRequest extends AuditModel {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String address;
    private String about;
    private boolean recurrent;
    private LocalDateTime foundationDate;
    private PatientTypeRequest patientType;
}
