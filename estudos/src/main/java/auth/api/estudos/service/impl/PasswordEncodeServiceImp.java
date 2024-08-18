package auth.api.estudos.service.impl;

import auth.api.estudos.service.PasswordEncodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncodeServiceImp implements PasswordEncodeService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String encodePassword(String senha) {
        return passwordEncoder.encode(senha);
    }

}
