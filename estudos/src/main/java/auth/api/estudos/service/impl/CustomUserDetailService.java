package auth.api.estudos.service.impl;

import auth.api.estudos.repository.CredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    CredencialRepository credencialRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Map<String, Object> user = credencialRepository.buscarCredenciaisDoUsuarioById(userEmail);
        if (user == null) {
            throw new UsernameNotFoundException("usuario não encontrado");
        }
        return new User(
                (String) user.get("email"),
                (String) user.get("senha"),
                new ArrayList<>()
        );
    }
}
