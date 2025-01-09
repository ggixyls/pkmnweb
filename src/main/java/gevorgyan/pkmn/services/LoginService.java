package gevorgyan.pkmn.services;

import gevorgyan.pkmn.services.IMPL.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public String login(String username, String rawPassword) throws CredentialException {
        if(!jdbcUserDetailsManager.userExists(username)){
            throw new UsernameNotFoundException("Username %s not found".formatted(username));
        }

        UserDetails user = jdbcUserDetailsManager.loadUserByUsername(username);

        if(!passwordEncoder.matches(rawPassword, user.getPassword())){
            throw new CredentialException("password is not valid");
        }

        return jwtService.createJwt(user.getUsername(), user.getAuthorities().iterator().next());
    }

}
