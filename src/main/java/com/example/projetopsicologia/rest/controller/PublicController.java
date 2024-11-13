package com.example.projetopsicologia.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.projetopsicologia.domain.entity.Usuario;
import com.example.projetopsicologia.domain.service.CustomUserDetailsService;
import com.example.projetopsicologia.domain.service.UsuarioService;
import com.example.projetopsicologia.dto.CadastroUsuarioDTO;
import com.example.projetopsicologia.dto.TokenDTO;
import com.example.projetopsicologia.dto.UserDTO;
import com.example.projetopsicologia.exception.SenhaInvalidaException;
import com.example.projetopsicologia.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class PublicController {
    
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioService usuarioService;

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody UserDTO userDTO) {
        try{
            Usuario usuario = Usuario.builder()
                    .login(userDTO.getUsername())
                    .senha(userDTO.getPassword()).build();
            customUserDetailsService.autenticar(usuario);
            String token = jwtTokenProvider.generateToken(usuario.getLogin());
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/cadastro") 
    public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDTO body) { 
        Usuario UsuarioSalvo = usuarioService.salvar(body.getUsuario(), body.getPermissoes()); 
        return ResponseEntity.ok(UsuarioSalvo); 
    
    }

}
