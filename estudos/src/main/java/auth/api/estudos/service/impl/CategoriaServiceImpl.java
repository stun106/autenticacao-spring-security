package auth.api.estudos.service.impl;

import auth.api.estudos.model.Categoria;
import auth.api.estudos.repository.CategoriaReporisoty;
import auth.api.estudos.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaReporisoty categoriaReporisoty;

    @Override
    public Categoria criarCategoria(Categoria categoria) {
        return categoriaReporisoty.save( categoria );
    }
}
