package auth.api.estudos.repository;

import auth.api.estudos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaReporisoty extends JpaRepository<Categoria, UUID> {
}
