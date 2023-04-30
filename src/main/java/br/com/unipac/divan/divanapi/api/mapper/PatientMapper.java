package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientResponse;
import br.com.unipac.divan.divanapi.model.domain.Gender;
import br.com.unipac.divan.divanapi.model.domain.MaritalStatus;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
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
    @Mapping(source = "patientTypeId", target = "patientType.id")
    @Mapping(source = "associationId", target = "association.id")

    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "documentId", target = "documentId")
    @Mapping(source = "documentDistrict", target = "documentDistrict")
    @Mapping(source = "documentDispatchDate", target = "documentDispatchDate")
    @Mapping(source = "socialId", target = "socialId")
    @Mapping(source = "socialIdDispatchDate", target = "socialIdDispatchDate")
    @Mapping(source = "maritalStatus", target = "maritalStatus")
    @Mapping(source = "mothersName", target = "mothersName")
    Patient from(PatientRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    PatientResponse to(Patient patient);

    List<PatientResponse> map(List<Patient> customers);
}
