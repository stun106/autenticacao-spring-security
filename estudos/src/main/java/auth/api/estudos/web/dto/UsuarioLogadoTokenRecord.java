package auth.api.estudos.web.dto;

public record UsuarioLogadoTokenRecord(Long idUsuario, String email, String senha, String nome, String role) {
}
