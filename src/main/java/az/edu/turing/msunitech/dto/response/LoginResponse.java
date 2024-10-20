package az.edu.turing.msunitech.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
        @NotBlank
        private String message;
        @NotBlank
        private String username;
        @NotBlank
        private  String accessToken;
        @NotBlank
        private String refreshToken;

        public LoginResponse(String message) {
                this.message = message;
        }
}
