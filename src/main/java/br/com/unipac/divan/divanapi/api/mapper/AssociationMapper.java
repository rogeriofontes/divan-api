package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.association.AssociationRequest;
import br.com.unipac.divan.divanapi.api.dto.response.association.AssociationResponse;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AssociationMapper {

    @InheritConfiguration
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "about", target = "about")
    @Mapping(source = "recurrent", target = "recurrent")
    @Mapping(source = "foundationDate", target = "foundationDate")
    @Mapping(source = "patientType", target = "patientType")
    Association from(AssociationRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    AssociationResponse to(Association association);

    List<AssociationResponse> map(List<Association> customers);
}
