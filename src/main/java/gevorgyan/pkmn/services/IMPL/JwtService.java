package gevorgyan.pkmn.services.IMPL;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;



@Slf4j
@Component
@RequiredArgsConstructor
public class JwtService {

    private final UserDetailsService userDetailsService;

    @Value("${token.secret}")
    private String  SECRET_KEY;

    @Value("${token.expiration}")
    private long TOKEN_EXPIRATION_MINUTE;

    public String createJwt(String username, GrantedAuthority authority) {

        System.out.println(authority.getAuthority());

        String jwt = JWT.create()
                .withIssuer("pkmn")
                .withSubject(username)
                .withClaim("authority", authority.getAuthority())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(50000000)))
                .sign(Algorithm.HMAC512(SECRET_KEY));
        return jwt;
    }

    public DecodedJWT verify(String jwt){
        try{
            JWTVerifier verifier = JWT
                    .require(Algorithm.HMAC512(SECRET_KEY))
                    .withIssuer("pkmn")
                    .build();

            log.info("JWT info: {}", jwt);

            DecodedJWT decodedJWT = verifier.verify(jwt);

            log.info("Decoded JWT subject: {}", decodedJWT.getSubject());

            if(Objects.isNull(userDetailsService.loadUserByUsername(decodedJWT.getSubject()))){
                return null;
            }

            return decodedJWT;
        } catch (JWTVerificationException e){
            return null;
        }
    }
}