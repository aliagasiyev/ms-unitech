package az.edu.turing.msunitech.service.impl;

import az.edu.turing.msunitech.dto.request.LoginRequest;
import az.edu.turing.msunitech.dto.request.RegisterRequest;
import az.edu.turing.msunitech.dto.response.LoginResponse;
import az.edu.turing.msunitech.dto.response.LogoutResponse;
import az.edu.turing.msunitech.dto.response.RegisterResponse;
import az.edu.turing.msunitech.service.AuthService;
import org.springframework.http.ResponseEntity;

public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseEntity<RegisterResponse> registerAccount(Long userId, RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public void logout(String refreshToken) {

    }

    @Override
    public ResponseEntity<LogoutResponse> refreshToken(String refreshToken) {
        return null;
    }
}
