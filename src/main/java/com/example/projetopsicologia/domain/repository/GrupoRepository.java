package com.example.projetopsicologia.domain.repository;

import java.util.Optional;

import com.example.projetopsicologia.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GrupoRepository extends JpaRepository<Grupo, String> {
    Optional<Grupo> findByNome(String nome);
}
