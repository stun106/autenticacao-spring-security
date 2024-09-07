package auth.api.estudos.web.controller;

import auth.api.estudos.jwt.JwtUserDetailsService;
import auth.api.estudos.model.Categoria;
import auth.api.estudos.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria (@RequestBody Categoria categoria) {
        Categoria estaCategoria = categoriaService.criarCategoria( categoria );
        return ResponseEntity.status( HttpStatus.CREATED ).body( estaCategoria );
    }
}
