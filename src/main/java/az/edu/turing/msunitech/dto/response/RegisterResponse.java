package az.edu.turing.msunitech.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    @NotNull
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String message;

    public RegisterResponse(String message) {
        this.message = message;
    }
}
