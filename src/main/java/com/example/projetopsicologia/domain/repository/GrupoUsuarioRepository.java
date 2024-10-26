package com.example.projetopsicologia.domain.repository;

import com.example.projetopsicologia.domain.entity.GrupoUsuario;
import com.example.projetopsicologia.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrupoUsuarioRepository extends JpaRepository<GrupoUsuario, String> {
    @Query("""
    select distinct g.nome from GrupoUsuario ug join ug.grupo g
    join ug.usuario u where u = ?1

""")
    List<String> findPermissoesByUsuario(Usuario usuario);
}
