package auth.api.estudos.service.impl;

import auth.api.estudos.jwt.JwtUserDetailsService;
import auth.api.estudos.model.Produto;
import auth.api.estudos.model.Usuario;
import auth.api.estudos.repository.ProdutoRepository;
import auth.api.estudos.service.ProdutoService;
import auth.api.estudos.service.UsuarioService;
import auth.api.estudos.service.exception.NullPointerAuthorizationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final UsuarioService usuarioService;
    private final JwtUserDetailsService jwtUserDetailsService;

    @Override
    public Produto criarProduto(Produto produto) {
        String emailUsuarioLogado = jwtUserDetailsService.getLoggedInUsername();
        Usuario usuarioLogado = usuarioService.buscarUsuarioPorEmail( emailUsuarioLogado );
        produto.setCriadoPor( usuarioLogado.getNome() );
        Produto produtoSalvo = produtoRepository.save( produto );

        if (produtoSalvo.getCategoria() == null ){
            throw new NullPointerAuthorizationException( "Para cadastrar um produto é nescessário vincula-lo a uma categoria..." );
        }
        produtoSalvo.setCategoria( produto.getCategoria() );

        return produtoSalvo;
    }

    @Override
    public List<Produto> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }
}
