package br.com.unipac.divan.divanapi.api.dto.request.psychological;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PsychologicalRequest  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String mobile;
    private String address;
    private Long associationId;
    private Long psychologicalSpecialityId;

}
