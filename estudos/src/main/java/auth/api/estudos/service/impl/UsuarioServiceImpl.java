package auth.api.estudos.service.impl;

import auth.api.estudos.model.Autorizacao;
import auth.api.estudos.model.Usuario;
import auth.api.estudos.repository.EnderecoRepository;
import auth.api.estudos.repository.UsuarioReprository;
import auth.api.estudos.service.UsuarioService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioReprository usuarioReprository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    private static String passwordEncode(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }

    @Override
    public Usuario criandoUsuario(Usuario usuario) {

        Usuario usuarioExiste = usuarioReprository.findByNome(usuario.getNome());
        if (usuarioExiste != null) {
            throw new EntityExistsException("Usuario já Existe...");
        }

        usuario.setSenha(passwordEncode(usuario.getSenha()));
        usuario.setRole(Autorizacao.USER);

        Usuario usuarioSalvo = usuarioReprository.save(usuario);

        if (usuario.getEndereco() != null && usuario.getEndereco().size() > 2) {
            throw new RuntimeException("O usuario so pode cadastrar 2 endereços...");
        }
        //salva apenas endereços unicos
        usuarioSalvo.getEndereco().forEach(endereco -> {
            endereco.setUsuario(usuario);
            enderecoRepository.save(endereco);
        });

        return usuarioSalvo;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioReprository.findUsuarioByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
    }
    @Override
    @Transactional(readOnly = true)
    public Autorizacao buscarRoleByEmail(String email) {
        return usuarioReprository.buscarRolePorEmail(email);
    }
}

