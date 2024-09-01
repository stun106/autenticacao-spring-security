package auth.api.estudos.jwt;

import auth.api.estudos.model.Usuario;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.UUID;

public class JwtUserDatails extends User {

    private Usuario usuario;

    public JwtUserDatails(Usuario usuario) {
        super(usuario.getEmail(), usuario.getSenha(), AuthorityUtils.createAuthorityList(usuario.getRole().name()));
        this.usuario = usuario;
    }

    private UUID getId() {
        return this.usuario.getIdUsuario();
    }
    private String getRole() {
        return this.usuario.getRole().name();
    }
}
