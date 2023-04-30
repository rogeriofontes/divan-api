package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.psychological.PsychologicalTypeRequest;
import br.com.unipac.divan.divanapi.api.dto.response.psychological.PsychologicalTypeResponse;
import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalType;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PsychologicalTypeMapper {

    @InheritConfiguration
    @Mapping(source = "description", target = "description")
    PsychologicalType from(PsychologicalTypeRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    PsychologicalTypeResponse to(PsychologicalType psychologicalType);

    List<PsychologicalTypeResponse> map(List<PsychologicalType> psychologicalTypes);
}
