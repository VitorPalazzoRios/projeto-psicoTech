package com.example.projetopsicologia.rest.controller;

import com.example.projetopsicologia.dto.CadastroUsuarioDTO;
import com.example.projetopsicologia.dto.UserDTO;
import com.example.projetopsicologia.domain.entity.Usuario;
import com.example.projetopsicologia.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDTO body) {
        Usuario UsuarioSalvo = usuarioService.salvar(body.getUsuario(), body.getPermissoes());
        return ResponseEntity.ok(UsuarioSalvo);
    }
    
    @GetMapping("/{login}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String login) {
        Usuario UsuarioSalvo = usuarioService.getUsuarioByLogin(login);

        return ResponseEntity.ok(UsuarioSalvo);
    }

    @GetMapping("/user")
    public UserDTO getUser(@AuthenticationPrincipal Usuario user) {
    return new UserDTO(user.getLogin(), user.getSenha());
    }
}