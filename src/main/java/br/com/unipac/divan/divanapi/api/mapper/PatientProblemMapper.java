package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientProblemRequest;
import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientRequest;
import br.com.unipac.divan.divanapi.api.dto.request.patient.ProblemTypeRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientProblemResponse;
import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientProblem;
import br.com.unipac.divan.divanapi.model.entities.patient.ProblemType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientProblemMapper {

    @InheritConfiguration
    @Mapping(source = "description", target = "description")
    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "problemTypeId", target = "problemType.id")
    @Mapping(source = "evolved", target = "evolved")
    PatientProblem from(PatientProblemRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    PatientProblemResponse to(PatientProblem patientProblem);

    List<PatientProblemResponse> map(List<PatientProblem> patientProblems);
}