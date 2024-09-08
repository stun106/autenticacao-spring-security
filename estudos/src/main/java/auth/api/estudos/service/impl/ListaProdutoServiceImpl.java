package auth.api.estudos.service.impl;

import auth.api.estudos.model.ListaProduto;
import auth.api.estudos.model.Produto;
import auth.api.estudos.repository.ListaProdutoRepository;
import auth.api.estudos.repository.ProdutoRepository;
import auth.api.estudos.service.ListaProdutoService;
import auth.api.estudos.service.exception.NullPointerAuthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListaProdutoServiceImpl implements ListaProdutoService {

    private final ListaProdutoRepository listaProdutoRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public ListaProduto criarListaProduto(ListaProduto listaProduto) {
        if (listaProduto.getProdutos().isEmpty()) {
            throw new NullPointerAuthorizationException( "Não é possivel Salvar essa Lista sem registrar um produto antes..." );
        }

        List<Produto> produtosCarregados = listaProduto.getProdutos().stream()
                .map(produto -> produtoRepository.findById(produto.getIdProduto())
                        .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + produto.getIdProduto())))
                .collect( Collectors.toList());

        listaProduto.setProdutos(produtosCarregados);

        listaProduto.getProdutos().forEach(produto -> produto.setListaProduto(listaProduto) );

        return listaProdutoRepository.save(listaProduto);
    }

    @Override
    public List<ListaProduto> buscarTodosProdutos() {
        return listaProdutoRepository.findAll();
    }
}
