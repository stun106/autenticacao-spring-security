package auth.api.estudos.web.controller;

import auth.api.estudos.model.ListaProduto;
import auth.api.estudos.service.ListaProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lista-produto")
public class ListaProdutoController {
    private final ListaProdutoService listaProdutoService;

    @PostMapping
    public ResponseEntity<ListaProduto> criarListaProduto (@RequestBody ListaProduto listaProduto){
        ListaProduto estaListaProduto = listaProdutoService.criarListaProduto( listaProduto );
        return ResponseEntity.status( HttpStatus.CREATED ).body( estaListaProduto );
    }

    @GetMapping
    public ResponseEntity<List<ListaProduto>> buscarTodasListasDeProduto () {
        return ResponseEntity.ok(listaProdutoService.buscarTodosProdutos());
    }
}
