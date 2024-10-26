package com.example.projetopsicologia.dto;

import com.example.projetopsicologia.domain.entity.Usuario;
import lombok.Data;

import java.util.List;
@Data  
public class CadastroUsuarioDTO {
    private Usuario usuario;
    private List<String> permissoes;
}