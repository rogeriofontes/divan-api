package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.association.AssociationRequest;
import br.com.unipac.divan.divanapi.api.dto.request.association.project.ProjectRequest;
import br.com.unipac.divan.divanapi.api.dto.response.association.project.ProjectResponse;
import br.com.unipac.divan.divanapi.constants.Constants;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import br.com.unipac.divan.divanapi.model.entities.association.project.Project;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @InheritConfiguration
    @Mapping(source = "name", target = "name")
    @Mapping(source = "shortDescription", target = "shortDescription")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "foundationDate", target = "foundationDate")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "about", target = "about")
    @Mapping(source = "online", target = "online")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "repeat", target = "repeat")
    @Mapping(source = "associationId", target = "association.id")
    Project from(ProjectRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    ProjectResponse to(Project project);

    List<ProjectResponse> map(List<Project> customers);
}
