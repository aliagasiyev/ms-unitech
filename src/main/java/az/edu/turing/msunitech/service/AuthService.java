package az.edu.turing.msunitech.service;

import az.edu.turing.msunitech.dto.request.LoginRequest;
import az.edu.turing.msunitech.dto.request.RegisterRequest;
import az.edu.turing.msunitech.dto.response.AuthResponse;
import az.edu.turing.msunitech.dto.response.LoginResponse;
import az.edu.turing.msunitech.dto.response.RegisterResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<RegisterResponse> registerAccount(RegisterRequest registerRequest);

    ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest);

    void logout(String refreshToken);

    ResponseEntity<AuthResponse> refreshToken(String refreshToken);

}
