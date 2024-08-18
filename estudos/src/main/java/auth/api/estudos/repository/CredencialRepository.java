package auth.api.estudos.repository;

import auth.api.estudos.model.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.UUID;

public interface CredencialRepository extends JpaRepository<Credencial, UUID> {
    @Query(value = "select u.id_usuario as idUsuario,\n" +
            "u.nome as nomeUsuario,\n" +
            "u.role,\n" +
            "c.id_credencial as idCredencial,\n" +
            "c.email,\n" +
            "c.senha\n" +
            "from usuario u\n" +
            "join credencial c on u.id_usuario = u.id_usuario\n" +
            "where c.email = :email", nativeQuery = true)
    public Map<String, Object> buscarCredenciaisDoUsuarioById(String email);

}
