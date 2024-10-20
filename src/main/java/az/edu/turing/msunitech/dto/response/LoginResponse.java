package az.edu.turing.msunitech.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginResponse(
        @NotBlank
        String message,
        @NotBlank
        String username,
        @NotBlank
        String accessToken,
        @NotBlank
        String refreshToken
) {
}
