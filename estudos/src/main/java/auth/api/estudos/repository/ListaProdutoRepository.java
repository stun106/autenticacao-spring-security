package auth.api.estudos.repository;

import auth.api.estudos.model.ListaProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListaProdutoRepository extends JpaRepository<ListaProduto, UUID> {
}
