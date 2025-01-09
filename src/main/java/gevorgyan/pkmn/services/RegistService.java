package gevorgyan.pkmn.services;

import gevorgyan.pkmn.models.Login;
import gevorgyan.pkmn.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistService {

    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    private final PasswordEncoder passwordEncoder;

    public void registerUser(Login request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserDto userDetails = UserDto.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .enabled(true)
                .build();

        jdbcUserDetailsManager.createUser(userDetails);
    }
}