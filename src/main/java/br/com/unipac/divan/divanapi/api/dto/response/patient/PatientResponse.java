package br.com.unipac.divan.divanapi.api.dto.response.patient;

import br.com.unipac.divan.divanapi.model.entities.association.Association;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PatientResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firtName;
    private String lastName;
    private String email;
    private String phone;
    private String mobile;
    private String address;
    private boolean recurrent;
    private PatientTypeResponse patientType;
    private Association association;
    //user_account
}
