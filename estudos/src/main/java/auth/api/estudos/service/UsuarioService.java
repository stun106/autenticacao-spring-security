package auth.api.estudos.service;

import auth.api.estudos.model.Autorizacao;
import auth.api.estudos.model.Usuario;
import org.springframework.data.jpa.repository.Query;


public interface UsuarioService {
    Usuario criandoUsuario (Usuario usuario);

    @Query(
            value = """
                    select u.id_usuario,c.id_credencial ,c.email ,c.senha , u."role" from usuario u
                    join credencial c on u.id_usuario = c.id_usuario\s
                    where c.email = :email;
            """,nativeQuery = true)
    Usuario buscarUsuarioPorEmail(String email);

    Autorizacao buscarRoleByEmail(String email);
}