package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientResponse;
import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @InheritConfiguration
    @Mapping(source = "firtName", target = "firtName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "mobile", target = "mobile")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "recurrent", target = "recurrent")
    @Mapping(source = "patientType", target = "patientType")
    Patient from(PatientRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    PatientResponse to(Patient patient);

    List<PatientResponse> map(List<Patient> customers);
}
