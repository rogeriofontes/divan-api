package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.association.AssociationRequest;
import br.com.unipac.divan.divanapi.api.dto.request.psychological.PsychologicalRequest;
import br.com.unipac.divan.divanapi.api.dto.request.psychological.PsychologicalSpecialityRequest;
import br.com.unipac.divan.divanapi.api.dto.response.psychological.PsychologicalResponse;
import br.com.unipac.divan.divanapi.model.entities.psychological.Psychological;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PsychologicalMapper {
    
    @InheritConfiguration
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "mobile", target = "mobile")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "associationId", target = "association.id")
    @Mapping(source = "psychologicalSpecialityId", target = "psychologicalSpeciality.id")
    Psychological from(PsychologicalRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    PsychologicalResponse to(Psychological patientType);

    List<PsychologicalResponse> map(List<Psychological> patientTypes);

}
