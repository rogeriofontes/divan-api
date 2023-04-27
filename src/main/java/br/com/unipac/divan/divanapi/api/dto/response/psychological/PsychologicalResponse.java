package br.com.unipac.divan.divanapi.api.dto.response.psychological;

import br.com.unipac.divan.divanapi.api.dto.response.association.AssociationResponse;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PsychologicalResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firtName;
    private String lastName;
    private String email;
    private String phone;
    private String mobile;
    private String address;
    @NotNull
    @ApiModelProperty(value = "Patient Type")
    private AssociationResponse association;
    @NotNull
    @ApiModelProperty(value = "Psychological Speciality")
    private PsychologicalSpecialityResponse psychologicalSpeciality;
}
