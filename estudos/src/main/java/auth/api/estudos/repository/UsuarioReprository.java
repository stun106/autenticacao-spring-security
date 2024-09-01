package auth.api.estudos.repository;

import auth.api.estudos.model.Autorizacao;
import auth.api.estudos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Repository

public interface UsuarioReprository extends JpaRepository<Usuario, UUID> {
    Usuario findByNome (String nome);


   Optional<Usuario> findUsuarioByEmail (String email);

    @Query("SELECT u.role FROM Usuario u WHERE u.email = :email")
    Autorizacao buscarRolePorEmail(String email);
}
