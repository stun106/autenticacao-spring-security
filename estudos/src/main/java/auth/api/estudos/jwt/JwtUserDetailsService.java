package auth.api.estudos.jwt;

import auth.api.estudos.model.Autorizacao;
import auth.api.estudos.model.Usuario;
import auth.api.estudos.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario userCredencial = usuarioService.buscarUsuarioPorEmail(email);
        return new JwtUserDatails(userCredencial);
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        throw new UsernameNotFoundException( "Usuario não esta Logado..." );
    }

    public JwtToken getTokenAuthenticated (String email) {
        Autorizacao role = usuarioService.buscarRoleByEmail(email);
        return JwtUtils.createToken(email,role.name());
    }
}
