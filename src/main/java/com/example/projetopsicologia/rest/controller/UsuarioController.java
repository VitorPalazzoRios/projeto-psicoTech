package com.example.projetopsicologia.rest.controller;
import com.example.projetopsicologia.domain.entity.Usuario;
import com.example.projetopsicologia.domain.repository.UsuarioRepository;
import com.example.projetopsicologia.domain.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String id) {
        Usuario UsuarioSalvo = usuarioService.getUsuarioById(id);

        return ResponseEntity.ok(UsuarioSalvo);
    }
    
        @GetMapping("/tipo/{tipo}")
        public ResponseEntity<List<Usuario>> getUsuarioByTipo(@PathVariable String tipo) {
            List<Usuario> UsuarioSalvo = usuarioService.getUsuarioByTipo(tipo);

            return ResponseEntity.ok(UsuarioSalvo);
        }
    
    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable @Valid String id, @RequestBody Usuario usuario) {
        usuarioRepository.findById(id).map(usuarioExistente -> {
                    usuario.setId(usuarioExistente.getId());
                    usuario.setPermissoes(usuarioExistente.getPermissoes());
                    usuarioRepository.save(usuario);
                    return usuarioExistente;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));

    }
   
    
}