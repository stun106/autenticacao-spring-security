package auth.api.estudos.service;

import auth.api.estudos.model.Endereco;
import auth.api.estudos.model.Usuario;
import auth.api.estudos.web.dto.UsuarioLogadoTokenRecord;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;

public interface UsuarioService {
    Usuario criandoUsuario (Usuario usuario);
}
