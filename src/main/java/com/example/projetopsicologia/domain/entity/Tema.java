package com.example.projetopsicologia.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table( name = "tema" )
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_tema")
    private int cod_tema;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "reflexao")
    private String reflexao;
    @OneToMany(mappedBy = "cod_tema", fetch = FetchType.LAZY)
    private List<Usuario> profissionais;



}
