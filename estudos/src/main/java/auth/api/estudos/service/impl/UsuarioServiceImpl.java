package auth.api.estudos.service.impl;

import auth.api.estudos.model.Autorizacao;
import auth.api.estudos.model.Usuario;
import auth.api.estudos.repository.EnderecoRepository;
import auth.api.estudos.repository.UsuarioReprository;
import auth.api.estudos.service.UsuarioService;
import auth.api.estudos.service.exception.EntityNotfoundException;
import auth.api.estudos.service.exception.NullPointerAuthorizationException;
import auth.api.estudos.service.exception.PasswordInvalidException;
import auth.api.estudos.service.exception.UniqueViolationExeception;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Format;
import java.util.UUID;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioReprository usuarioReprository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    private static String passwordEncode(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
    private static boolean passwordDecode(String senhaAtual, String senha) {
        return new BCryptPasswordEncoder().matches(senhaAtual, senha);
    }

    @Override
    public Usuario criandoUsuario(Usuario usuario) {

        Usuario usuarioExiste = usuarioReprository.findByNome(usuario.getNome());
        if (usuarioExiste != null) {
            throw new UniqueViolationExeception("Usuario já Existe...");
        }

        usuario.setSenha(passwordEncode(usuario.getSenha()));
        usuario.setRole(Autorizacao.USER);

        Usuario usuarioSalvo = usuarioReprository.save(usuario);

        if (usuario.getEndereco() != null && usuario.getEndereco().size() > 2) {
            throw new NullPointerAuthorizationException("O usuario so pode cadastrar 2 endereços...");
        }
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

    @Override
    public Usuario alterarSenha(UUID idUsuario, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)){
            log.error( "Confirmação de senha invalida..." );
            throw new PasswordInvalidException("Confirmação de senha Invalida...");
        }

        Usuario esteUsuario = usuarioReprository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotfoundException( "Usuario não encontrado..." ) );
        if (!(passwordDecode( senhaAtual, esteUsuario.getSenha() ))) throw new PasswordInvalidException( "As credenciais não conferem, verifique seus dados..." );
        System.out.println(esteUsuario.getNome());
        esteUsuario.setSenha( passwordEncode( novaSenha ) );
        usuarioReprository.save( esteUsuario );

        return esteUsuario;
    }
}

