package com.example.projetopsicologia.rest.controller;
import com.example.projetopsicologia.domain.entity.Usuario;
import com.example.projetopsicologia.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    
    @GetMapping("/{login}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String login) {
        Usuario UsuarioSalvo = usuarioService.getUsuarioByLogin(login);

        return ResponseEntity.ok(UsuarioSalvo);
    }

    
   
    
}