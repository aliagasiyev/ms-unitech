package az.edu.turing.msunitech.service.impl;

import az.edu.turing.msunitech.domain.entity.UserEntity;
import az.edu.turing.msunitech.domain.repository.UserRepository;
import az.edu.turing.msunitech.dto.request.LoginRequest;
import az.edu.turing.msunitech.dto.request.RegisterRequest;
import az.edu.turing.msunitech.dto.response.LoginResponse;
import az.edu.turing.msunitech.dto.response.LogoutResponse;
import az.edu.turing.msunitech.dto.response.RegisterResponse;
import az.edu.turing.msunitech.mapper.UserMapper;
import az.edu.turing.msunitech.securityconfig.JwtTokenProvider;
import az.edu.turing.msunitech.service.AuthService;
import az.edu.turing.msunitech.util.PasswordEncoderUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper=UserMapper.INSTANCE;
    PasswordEncoder passwordEncoderUtil;
    JwtTokenProvider jwtTokenProvider;


    @Override
    public ResponseEntity<RegisterResponse> registerAccount(RegisterRequest registerRequest) {
        if (userRepository.checkByUsername(registerRequest.username())){
            return ResponseEntity.badRequest().body(new RegisterResponse("Username is already taken!"));
        }
      UserEntity userEntity= userRepository.save(userMapper.toUserEntity(registerRequest));
        new RegisterResponse();
        return ResponseEntity.ok(RegisterResponse.builder().id(userEntity.getId()).username(userEntity.getUsername()).message("Registered successfully").build());

    }

    @Override
    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        if (!passwordEncoderUtil.matches(loginRequest.password(), loginRequest.password())) {
            return ResponseEntity.badRequest().body(new LoginResponse("Invalid credentials"));
        }

        String token = jwtTokenProvider.createAccessToken(loginRequest.username());
        String refreshToken = jwtTokenProvider.createRefreshToken(loginRequest.username());

        LoginResponse loginResponse = LoginResponse.builder()
                .message("Login successful")
                .username(loginRequest.username())
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.ok(loginResponse);
    }

    @Override
    public void logout(String refreshToken) {

    }

    @Override
    public ResponseEntity<LogoutResponse> refreshToken(String refreshToken) {
        return null;
    }
}
