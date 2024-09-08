package auth.api.estudos.service;

import auth.api.estudos.model.ListaProduto;

import java.util.List;

public interface ListaProdutoService {
    ListaProduto criarListaProduto (ListaProduto listaProduto);
    List<ListaProduto> buscarTodosProdutos ();
}
