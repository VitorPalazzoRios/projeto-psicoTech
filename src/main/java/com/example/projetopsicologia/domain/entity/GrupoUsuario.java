package com.example.projetopsicologia.domain.entity;


import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table( name = "grupo_usuario" )
public class GrupoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    public GrupoUsuario(String id, Usuario usuario, Grupo grupo) {
        this.id = id;
        this.usuario = usuario;
        this.grupo = grupo;
    }

    public GrupoUsuario(Usuario usuario, Grupo grupo) {
        this.usuario = usuario;
        this.grupo = grupo;
    }

    public GrupoUsuario() {
    }
}