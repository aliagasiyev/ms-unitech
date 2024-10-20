package az.edu.turing.msunitech.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordEncoderUtil() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public String encodePassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public boolean matchesPassword(String plainPassword, String encodedPassword) {
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }
}
