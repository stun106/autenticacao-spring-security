package auth.api.estudos.service;

import auth.api.estudos.model.Autorizacao;
import auth.api.estudos.model.Usuario;

import java.util.UUID;


public interface UsuarioService {
    Usuario criandoUsuario (Usuario usuario);

    Usuario buscarUsuarioPorNome (String nome);

    Usuario buscarUsuarioPorEmail(String email);

    Autorizacao buscarRoleByEmail(String email);

    Usuario alterarSenha (UUID idUsuario, String senhaAtual, String novaSenha, String ConfirmaSenha);
}