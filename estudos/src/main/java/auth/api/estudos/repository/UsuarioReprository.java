package auth.api.estudos.repository;

import auth.api.estudos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;


@Repository
public interface UsuarioReprository extends JpaRepository<Usuario, UUID> {
    public Usuario findByNome (String nome);


}
