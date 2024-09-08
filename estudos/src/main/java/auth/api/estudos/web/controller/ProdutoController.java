package auth.api.estudos.web.controller;

import auth.api.estudos.model.Produto;
import auth.api.estudos.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> criarProduto (@RequestBody Produto produto) {
        Produto esteProduto = produtoService.criarProduto( produto );
        return ResponseEntity.status( HttpStatus.CREATED ).body( esteProduto );
    }
    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodosProdutos () {
        return ResponseEntity.ok(produtoService.buscarTodosProdutos());
    }
}
