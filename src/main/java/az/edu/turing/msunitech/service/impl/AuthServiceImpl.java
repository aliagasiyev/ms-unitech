package az.edu.turing.msunitech.service.impl;

import az.edu.turing.msunitech.domain.entity.UserEntity;
import az.edu.turing.msunitech.domain.repository.UserRepository;
import az.edu.turing.msunitech.dto.request.LoginRequest;
import az.edu.turing.msunitech.dto.request.RegisterRequest;
import az.edu.turing.msunitech.dto.response.AuthResponse;
import az.edu.turing.msunitech.dto.response.LoginResponse;
import az.edu.turing.msunitech.dto.response.RegisterResponse;
import az.edu.turing.msunitech.mapper.UserMapper;
import az.edu.turing.msunitech.securityconfig.JwtTokenProvider;
import az.edu.turing.msunitech.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoderUtil;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    public ResponseEntity<RegisterResponse> registerAccount(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.username())) {
            return ResponseEntity.badRequest().body(new RegisterResponse("Username is already taken!"));
        }
        UserEntity user = userMapper.toUserEntity(registerRequest);
        user.setPassword(passwordEncoderUtil.encode(registerRequest.password()));
        UserEntity userEntity = userRepository.save(user);
        new RegisterResponse();
        return ResponseEntity.ok(RegisterResponse.builder().id(userEntity.getId()).username(userEntity.getUsername()).message("Registered successfully").build());

    }

    @Override
    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.username()).orElseThrow(() -> new RuntimeException("Username or password is incorrect!"));

        if (!passwordEncoderUtil.matches(loginRequest.password(), user.getPassword())) {
            return ResponseEntity.badRequest().body(new LoginResponse("Invalid credentials"));
        }

        String token = jwtTokenProvider.createAccessToken(loginRequest.username());
        String refreshToken = jwtTokenProvider.createRefreshToken(loginRequest.username());

        String redisKey = "refreshToken:" + loginRequest.username();
        redisTemplate.opsForValue().set(redisKey, refreshToken);


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
        String username = jwtTokenProvider.getUsername(refreshToken);
        String redisKey = "refreshToken:" + username;
        redisTemplate.delete(redisKey);
    }

    @Override
    public ResponseEntity<AuthResponse> refreshToken(String refreshToken) {
        AuthResponse authResponse = jwtTokenProvider.refreshToken(refreshToken);
        return ResponseEntity.ok(authResponse);
    }
}
