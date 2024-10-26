package com.example.projetopsicologia.domain.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Data
@Table( name = "usuario" )
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
   
    @Column(name = "login")
    @NotEmpty(message = "login_usuario não pode ser vazio")
    private String login;
   
    @Column(name = "senha")
    @NotEmpty(message = "Senha não pode ser vazio")
    private String senha;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "rg")
    private String rg;

    @Column(name = "email")
    private String email;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "uf")
    private String uf;

    @Column(name = "cep")
    private String cep;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "data_nascimento")
    private LocalDate data_nascimento;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "celular")
    private String celular;

    @Column(name = "profissao")
    private String profissao;

    @Column(name = "especializacao")
    private String especializacao;

    @Column(name = "CRP")
    private String CRP;

    @Column(name = "CRM")
    private String CRM;

    @Column(name = "caminho_foto")
    private String caminho_foto;

    @ManyToOne
    @JoinColumn(name = "cod_tema")
    private Tema cod_tema;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "tipo")
    private String tipo;

    @Transient
    private List<String> permissoes;

}