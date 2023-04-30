package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.schedule.ScheduleSessionRequest;
import br.com.unipac.divan.divanapi.api.dto.response.schedule.ScheduleSessionResponse;
import br.com.unipac.divan.divanapi.model.entities.schedule.ScheduleSession;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleSessionMapper {

    @InheritConfiguration
    @Mapping(source = "shortDescription", target = "shortDescription")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "answered", target = "answered")
    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "psychologicalId", target = "psychological.id")
    ScheduleSession from(ScheduleSessionRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    ScheduleSessionResponse to(ScheduleSession patient);

    List<ScheduleSessionResponse> map(List<ScheduleSession> customers);
}
