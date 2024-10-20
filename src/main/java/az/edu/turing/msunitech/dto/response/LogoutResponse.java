package az.edu.turing.msunitech.dto.response;

import lombok.Builder;

@Builder
public record LogoutResponse(
        String message,
        String token,
        String refreshToken
) {
}
