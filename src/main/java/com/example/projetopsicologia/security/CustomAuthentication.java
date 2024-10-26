package com.example.projetopsicologia.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomAuthentication implements Authentication {

    private final IdentificacaoUsuario identificacaoUsuario;
    public CustomAuthentication(IdentificacaoUsuario identificacaoUsuario) {
        if (identificacaoUsuario == null){
            throw new ExceptionInInitializerError("Não é possivel criar um custom authentication sem a indentificação do usuario") ;
        }
        this.identificacaoUsuario = identificacaoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.identificacaoUsuario.getPermissoes()
                .stream().map(permissao -> new SimpleGrantedAuthority(permissao)).collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.identificacaoUsuario;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Já estamos logado");
    }

    @Override
    public String getName() {
        return this.identificacaoUsuario.getNome();
    }
}
