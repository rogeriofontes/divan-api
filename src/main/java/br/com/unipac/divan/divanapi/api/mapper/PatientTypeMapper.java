package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientTypeRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientResponse;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientTypeResponse;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientType;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientTypeMapper {

    @InheritConfiguration
    @Mapping(source = "description", target = "description")
    PatientType from(PatientTypeRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    PatientTypeResponse to(PatientType patientType);

    List<PatientTypeResponse> map(List<PatientType> patientTypes);
}
