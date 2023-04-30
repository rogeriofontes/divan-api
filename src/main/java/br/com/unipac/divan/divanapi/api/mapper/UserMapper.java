package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.user.UserRequest;
import br.com.unipac.divan.divanapi.api.dto.response.user.UserResponse;
import br.com.unipac.divan.divanapi.model.entities.user.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @InheritConfiguration
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "profiles", target = "profiles")
    @Mapping(source = "lastAccess", target = "lastAccess")
    User from(UserRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    UserResponse to(User project);

    List<UserResponse> map(List<User> customers);
}
