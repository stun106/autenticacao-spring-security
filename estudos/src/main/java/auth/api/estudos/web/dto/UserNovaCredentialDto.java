package auth.api.estudos.web.dto;

import java.util.UUID;

public record UserNovaCredentialDto(UUID idUsuario, String senhaAtual, String novaSenha, String confirmaSenha) {
}
