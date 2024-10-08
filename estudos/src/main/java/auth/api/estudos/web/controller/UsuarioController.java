package auth.api.estudos.web.controller;

import auth.api.estudos.model.Autorizacao;
import auth.api.estudos.model.Usuario;
import auth.api.estudos.service.UsuarioService;
import auth.api.estudos.web.dto.UserNovaCredentialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> create (@RequestBody Usuario novoUsuario) {
        usuarioService.criandoUsuario(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping()
    public Usuario buscarUsuarioPorEmail(@RequestParam("email") String email) {
        return usuarioService.buscarUsuarioPorEmail(email);
    }
    @GetMapping("/role/{email}")
    public Autorizacao buscarRolePorEmail(@PathVariable String email) {
        return usuarioService.buscarRoleByEmail(email);
    }

    @PutMapping("/alterar-senha/{idUsuario}")
    public ResponseEntity<Usuario> alterarSenha(@PathVariable UUID idUsuario, @RequestBody UserNovaCredentialDto userNovaCredentialDto) {
        Usuario usuarioComSenhaAlterada = usuarioService.alterarSenha(
                userNovaCredentialDto.idUsuario(),
                userNovaCredentialDto.senhaAtual(),
                userNovaCredentialDto.novaSenha(),
                userNovaCredentialDto.confirmaSenha()
        );
        return ResponseEntity.status( HttpStatus.OK ).body( usuarioComSenhaAlterada );
    }
}
