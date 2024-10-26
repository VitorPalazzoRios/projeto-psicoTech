package com.example.projetopsicologia.config;

import com.example.projetopsicologia.domain.security.CustomAuthentication;
import com.example.projetopsicologia.domain.security.IdentificacaoUsuario;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


import java.util.List;
@Component
public class SenhaMasterAtenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var login_usuario = authentication.getName();
        var senha = authentication.getCredentials().toString();


        String login_usuarioMaster = "master";
        String senhaMaster = "@321";

        IdentificacaoUsuario identificacaoUsuario = new IdentificacaoUsuario("Sou Master" , "Master", "login_usuario Master", List.of("ROLE_ADMIN"));

        if (login_usuario.equals(login_usuarioMaster) && senha.equals(senhaMaster)) {
            return new CustomAuthentication(identificacaoUsuario);
        }


        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
