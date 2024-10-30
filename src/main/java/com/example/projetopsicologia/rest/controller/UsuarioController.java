package com.example.projetopsicologia.rest.controller;
import com.example.projetopsicologia.dto.CadastroUsuarioDTO;
import com.example.projetopsicologia.dto.TokenDTO;
import com.example.projetopsicologia.dto.UserDTO;
import com.example.projetopsicologia.exception.SenhaInvalidaException;
import com.example.projetopsicologia.security.JwtTokenProvider;
import com.example.projetopsicologia.domain.entity.Usuario;
import com.example.projetopsicologia.domain.service.CustomUserDetailsService;
import com.example.projetopsicologia.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

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
    
   
    
}