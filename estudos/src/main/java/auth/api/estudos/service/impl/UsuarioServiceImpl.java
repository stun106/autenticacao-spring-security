package auth.api.estudos.service.impl;

import auth.api.estudos.model.Autorizacao;
import auth.api.estudos.model.Credencial;
import auth.api.estudos.model.Endereco;
import auth.api.estudos.model.Usuario;
import auth.api.estudos.repository.CredencialRepository;
import auth.api.estudos.repository.EnderecoRepository;
import auth.api.estudos.repository.UsuarioReprository;
import auth.api.estudos.service.PasswordEncodeService;
import auth.api.estudos.service.UsuarioService;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioReprository usuarioReprository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PasswordEncodeService passwordEncodeService;
    @Autowired
    CredencialRepository credencialRepository;

    @Override
    @Transactional
    public Usuario criandoUsuario(Usuario usuario) {
        Usuario novoUsuario = usuarioReprository.findByNome(usuario.getNome());
        if (novoUsuario != null) {
            throw new EntityExistsException("Usuario já existe.");
        }
            usuario.setRole(Autorizacao.USER);
            Usuario esteUsuario = usuarioReprository.save(usuario);

            Credencial credencialDoUsuario = credencialRepository.save(esteUsuario.getCredencial());
            esteUsuario.getCredencial().setUsuario(esteUsuario);
            esteUsuario.getCredencial().setSenha(passwordEncodeService.encodePassword(esteUsuario.getCredencial().getSenha()));
            esteUsuario.setCredencial(credencialDoUsuario);

            Set<Endereco> estesEnderecos = new HashSet<>();
            if (esteUsuario.getEnderecos().size() <= 2) {
                for (Endereco endereco : esteUsuario.getEnderecos()) {
                    Endereco esteEndereco = enderecoRepository.save(endereco);
                    estesEnderecos.add(esteEndereco);
                }
                esteUsuario.setEnderecos(estesEnderecos);
                return esteUsuario;
            } else {
                throw new RuntimeException("No máximo dois endereços.");
            }
    }
}

