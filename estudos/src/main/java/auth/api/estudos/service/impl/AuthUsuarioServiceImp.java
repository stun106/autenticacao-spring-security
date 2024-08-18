package auth.api.estudos.service.impl;

import auth.api.estudos.security.JwtTokenProvider;
import auth.api.estudos.service.AuthUsuarioService;
import auth.api.estudos.web.dto.AuthUsuarioRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthUsuarioServiceImp implements AuthUsuarioService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProviders;

    @Override
    public String autenticacao(AuthUsuarioRecord authUsuarioRecord) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authUsuarioRecord.email(),
                        authUsuarioRecord.senha()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProviders.createToken(authentication.getName());
    }
}
