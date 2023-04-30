package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.user.ProfileRequest;
import br.com.unipac.divan.divanapi.api.dto.response.user.ProfileResponse;
import br.com.unipac.divan.divanapi.model.entities.user.Profile;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @InheritConfiguration
    @Mapping(source = "role", target = "role")
    Profile from(ProfileRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    ProfileResponse to(Profile project);

    List<ProfileResponse> map(List<Profile> customers);
}
