package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.patient.ProblemTypeRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.ProblemTypeResponse;
import br.com.unipac.divan.divanapi.model.entities.patient.ProblemType;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProblemTypeMapper {

    @InheritConfiguration
    @Mapping(source = "description", target = "description")
    ProblemType from(ProblemTypeRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    ProblemTypeResponse to(ProblemType patientType);

    List<ProblemTypeResponse> map(List<ProblemType> patientTypes);
}
