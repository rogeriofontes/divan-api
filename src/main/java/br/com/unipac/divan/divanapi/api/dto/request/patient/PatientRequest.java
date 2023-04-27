package br.com.unipac.divan.divanapi.api.dto.request.patient;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PatientRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firtName;
    private String lastName;
    private String email;
    private String phone;
    private String mobile;
    private String address;
    private boolean recurrent;
    private Long patientTypeId;
    private Long associationId;
    //user_account
}
