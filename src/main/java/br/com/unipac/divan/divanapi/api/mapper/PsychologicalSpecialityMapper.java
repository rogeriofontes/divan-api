package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.psychological.PsychologicalSpecialityRequest;
import br.com.unipac.divan.divanapi.api.dto.response.psychological.PsychologicalResponse;
import br.com.unipac.divan.divanapi.api.dto.response.psychological.PsychologicalSpecialityResponse;
import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalSpeciality;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PsychologicalSpecialityMapper {
    @InheritConfiguration
    @Mapping(source = "description", target = "description")
    PsychologicalSpeciality from(PsychologicalSpecialityRequest request);
    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    PsychologicalSpecialityResponse to(PsychologicalSpeciality psychologicalSpeciality);
    List<PsychologicalSpecialityResponse> map(List<PsychologicalSpeciality> psychologicalSpecialitys);

}
