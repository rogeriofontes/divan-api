package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.authentication.AuthenticationRequest;
import br.com.unipac.divan.divanapi.api.dto.response.authentication.AuthenticationResponse;
import br.com.unipac.divan.divanapi.model.entities.user.User;
import br.com.unipac.divan.divanapi.util.JWTUtil;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {

    @InheritConfiguration
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User from(AuthenticationRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(source = "profiles", target = "roles")
    @Mapping(source = "id", target = "userId")
    @Mapping(constant = JWTUtil.TOKEN_PREFIX, target = "tokenType")
    //@Mapping(target = "token", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, defaultExpression = "java(getToken(user.getEmail()))")
    AuthenticationResponse to(User user);

    List<AuthenticationResponse> map(List<User> requests);
    /*default String getToken(String email) {
        if (email == null) return null;
        return JWTUtil.createToken(email);
    }*/
}
