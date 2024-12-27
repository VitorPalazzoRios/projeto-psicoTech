package com.example.projetopsicologia.domain.repository;


import com.example.projetopsicologia.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findBylogin(String login);
    List<Usuario> findByTipo(String tipo);
}