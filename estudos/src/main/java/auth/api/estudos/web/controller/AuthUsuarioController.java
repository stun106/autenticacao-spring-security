package auth.api.estudos.web.controller;

import auth.api.estudos.service.AuthUsuarioService;
import auth.api.estudos.web.dto.AuthUsuarioRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
public class AuthUsuarioController {
    @Autowired
    private AuthUsuarioService authUsuarioService;

    @PostMapping
    public ResponseEntity<?> autheticacaoDeUsuario (@RequestBody AuthUsuarioRecord authUsuarioRecord) {
       String token = authUsuarioService.autenticacao(authUsuarioRecord);
       Map<String,String> tokenResponse = new HashMap<>();
       tokenResponse.put("token", token);
       return ResponseEntity.ok(tokenResponse);
    }
}
