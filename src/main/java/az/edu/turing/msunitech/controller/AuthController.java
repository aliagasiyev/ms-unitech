package az.edu.turing.msunitech.controller;

import az.edu.turing.msunitech.dto.request.LoginRequest;
import az.edu.turing.msunitech.dto.request.RegisterRequest;
import az.edu.turing.msunitech.dto.response.AuthResponse;
import az.edu.turing.msunitech.dto.response.LoginResponse;
import az.edu.turing.msunitech.dto.response.RegisterResponse;
import az.edu.turing.msunitech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        return authService.registerAccount(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        return authService.loginUser(loginRequest);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String refreshToken = authorizationHeader.replace("Bearer ", "");
        authService.logout(refreshToken);
        return ResponseEntity.ok("Logged out successfully");
    }
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestHeader("Authorization") String authorizationHeader) {
        return authService.refreshToken(authorizationHeader.replace("Bearer ", ""));
    }
}
