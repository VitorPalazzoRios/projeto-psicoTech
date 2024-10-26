package com.example.projetopsicologia.domain.repository;

import com.example.projetopsicologia.domain.entity.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Integer> {
}
