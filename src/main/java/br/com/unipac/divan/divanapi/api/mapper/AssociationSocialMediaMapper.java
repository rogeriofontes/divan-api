package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.association.AssociationSocialMediaRequest;
import br.com.unipac.divan.divanapi.api.dto.response.association.AssociationSocialMediaResponse;
import br.com.unipac.divan.divanapi.model.entities.association.AssociationSocialMedia;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface AssociationSocialMediaMapper {

    @InheritConfiguration
    @Mapping(source = "domain", target = "domain")
    @Mapping(source = "link", target = "link")
    @Mapping(source = "logo", target = "logo")
    @Mapping(source = "twitter", target = "twitter")
    @Mapping(source = "facebook", target = "facebook")
    @Mapping(source = "instagram", target = "instagram")
    @Mapping(source = "associationId", target = "association.id")
    AssociationSocialMedia from(AssociationSocialMediaRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    AssociationSocialMediaResponse to(AssociationSocialMedia associationSocialMedia);

    List<AssociationSocialMediaResponse> map(List<AssociationSocialMedia> associationSocialMedias);
}
