package com.example.projetopsicologia.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentificacaoUsuario {
    private String id;
    private String nome;
    private String login_usuario;
    private List<String> permissoes;
    private String senha;

    public IdentificacaoUsuario(String id, String nome, String login_usuario, List<String> permissoes) {
        this.id = id;
        this.nome = nome;
        this.login_usuario = login_usuario;
        this.permissoes = permissoes;
    }

    public List<String> getPermissoes() {
        if (permissoes == null) {
            permissoes = new ArrayList<>();
        }
        return permissoes;
    }
}
