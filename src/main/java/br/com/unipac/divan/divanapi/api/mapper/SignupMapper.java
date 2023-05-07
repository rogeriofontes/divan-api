package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.authentication.SignupRequest;
import br.com.unipac.divan.divanapi.api.dto.response.authentication.AuthenticationResponse;
import br.com.unipac.divan.divanapi.api.dto.response.authentication.SignupResponse;
import br.com.unipac.divan.divanapi.model.entities.user.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SignupMapper {

    @InheritConfiguration
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User from(SignupRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", expression = "java(user.getId())")
    SignupResponse toSignup(User user);
}
