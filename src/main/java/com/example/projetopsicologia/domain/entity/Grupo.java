package com.example.projetopsicologia.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table( name = "grupo" )
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
}
