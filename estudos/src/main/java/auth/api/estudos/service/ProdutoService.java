package auth.api.estudos.service;

import auth.api.estudos.model.Produto;

import java.util.List;

public interface ProdutoService {

    Produto criarProduto (Produto produto);

    List<Produto> buscarTodosProdutos ();
}
