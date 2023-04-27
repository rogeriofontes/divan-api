package br.com.unipac.divan.divanapi.api.dto.request.association;

import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientTypeRequest;
import br.com.unipac.divan.divanapi.constants.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class AssociationRequest  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String address;
    private String about;
    private boolean recurrent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DATE)
    private LocalDateTime foundationDate;
}
