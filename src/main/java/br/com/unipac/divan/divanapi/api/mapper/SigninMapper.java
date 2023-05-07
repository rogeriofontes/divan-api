package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.authentication.AuthenticationRequest;
import br.com.unipac.divan.divanapi.api.dto.response.authentication.AuthenticationResponse;
import br.com.unipac.divan.divanapi.api.dto.response.authentication.SignupResponse;
import br.com.unipac.divan.divanapi.model.entities.user.User;
import br.com.unipac.divan.divanapi.util.JWTUtil;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SigninMapper {

    @InheritConfiguration
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User from(AuthenticationRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", expression = "java(user.getId())")
    //@Mapping(target = "accessToken", expression = "java(token(user.getEmail()))")
    @Mapping(target = "tokenType", constant = "Bearer")
    AuthenticationResponse to(User user);

   // default String token(String email){
   //     return JWTUtil.createToken(email);
    //}
}
