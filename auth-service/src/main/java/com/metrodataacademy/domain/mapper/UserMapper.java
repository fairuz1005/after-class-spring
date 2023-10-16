package com.metrodataacademy.domain.mapper;

import com.metrodataacademy.domain.dto.response.ResValidateTokenDto;
import com.metrodataacademy.domain.entity.Role;
import com.metrodataacademy.domain.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ResValidateTokenDto userToResValidateTokenDto(User user);

    @AfterMapping
    default void mappingRoles(@MappingTarget ResValidateTokenDto resValidateTokenDto, User user) {
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRole()) {
            roles.add(role.getName());
        }
        resValidateTokenDto.setRoles(roles);
    }
}
