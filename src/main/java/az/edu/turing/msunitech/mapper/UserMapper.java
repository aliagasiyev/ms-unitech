package az.edu.turing.msunitech.mapper;


import az.edu.turing.msunitech.domain.entity.UserEntity;
import az.edu.turing.msunitech.dto.request.LoginRequest;
import az.edu.turing.msunitech.dto.request.RegisterRequest;
import az.edu.turing.msunitech.dto.response.LoginResponse;
import az.edu.turing.msunitech.dto.response.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
        // No id for login request
    UserEntity toUserEntity(LoginRequest loginRequest);

    @Mapping(target = "message", source = "username")
        // Example mapping, adjust as necessary
    RegisterResponse toUserDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
        // ID will be auto-generated
    UserEntity toUserEntity(RegisterRequest registerRequest);

    LoginRequest toLoginRequest(UserEntity userEntity);

    @Mapping(target = "password", ignore = true)
        // No password in response
    UserEntity toUserEntity(RegisterResponse registerResponse);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserEntity toUserEntity(LoginResponse loginResponse);
}

