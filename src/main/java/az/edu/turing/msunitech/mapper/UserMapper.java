package az.edu.turing.msunitech.mapper;


import az.edu.turing.msunitech.domain.entity.UserEntity;
import az.edu.turing.msunitech.dto.request.LoginRequest;
import az.edu.turing.msunitech.dto.request.RegisterRequest;
import az.edu.turing.msunitech.dto.response.LoginResponse;
import az.edu.turing.msunitech.dto.response.RegisterResponse;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
    UserEntity toUserEntity(LoginRequest loginRequest);
    RegisterResponse toUserDto(UserEntity userEntity);

    UserEntity toUserEntity(RegisterRequest registerRequest);
    LoginRequest toLoginRequest(UserEntity userEntity);
    UserEntity toUserEntity(RegisterResponse registerResponse);
    UserEntity toUserEntity(LoginResponse loginResponse);
    LoginResponse toLoginResponse(UserEntity userEntity);

}
