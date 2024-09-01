package auth.api.estudos.web.controller;

import auth.api.estudos.jwt.JwtToken;
import auth.api.estudos.jwt.JwtUserDetailsService;
import auth.api.estudos.web.dto.AuthUsuarioRecord;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticacaoController {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> autenticar (@RequestBody AuthUsuarioRecord authUsuarioRecord, HttpServletRequest request) {
        log.info("processo de authenticação pelo login {}", authUsuarioRecord.email());
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authUsuarioRecord.email(), authUsuarioRecord.senha());
            authenticationManager.authenticate(authenticationToken);

            JwtToken token = jwtUserDetailsService.getTokenAuthenticated(authUsuarioRecord.email());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (AuthenticationException ex) {
            log.warn("Credencial invalida por email {}", authUsuarioRecord.email());
        }
        System.out.println(authUsuarioRecord.senha());
        return ResponseEntity.badRequest().body("Credenciais Invalidas");
    }
}
