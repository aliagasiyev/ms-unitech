package az.edu.turing.msunitech.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RegisterResponse(
        @NotNull
        Long id,
        @NotBlank
        String username,
        @NotBlank
        String message
) {
}
