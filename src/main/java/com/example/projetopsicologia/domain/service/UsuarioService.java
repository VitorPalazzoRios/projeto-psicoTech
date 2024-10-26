package com.example.projetopsicologia.domain.service;

import com.example.projetopsicologia.domain.entity.Grupo;
import com.example.projetopsicologia.domain.entity.GrupoUsuario;
import com.example.projetopsicologia.domain.entity.Usuario;
import com.example.projetopsicologia.domain.repository.GrupoRepository;
import com.example.projetopsicologia.domain.repository.GrupoUsuarioRepository;
import com.example.projetopsicologia.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    private final GrupoUsuarioRepository grupoUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario, List<String> grupos) {
        String SenhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(SenhaCriptografada);
        usuarioRepository.save(usuario);

        List<GrupoUsuario> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
            Optional<Grupo> PossivelGrupo = grupoRepository.findByNome(nomeGrupo);
            if (PossivelGrupo.isPresent()) {
                Grupo grupo = PossivelGrupo.get();
                return new GrupoUsuario(usuario, grupo);
            }
            return null;
        }).filter(grupo -> grupo != null).collect(Collectors.toList());
        grupoUsuarioRepository.saveAll(listaUsuarioGrupo);
        return usuario;

    }

    public Usuario obterUsuarioComPermissoes(String login){
        Optional<Usuario> usuarioOptional = usuarioRepository.findBylogin(login);
        if (usuarioOptional.isEmpty()) {
            return null;
        }
        Usuario usuario = usuarioOptional.get();
        List<String> permissoesByUsuario = grupoUsuarioRepository.findPermissoesByUsuario(usuario);
        usuario.setPermissoes(permissoesByUsuario);
        return usuario;
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioByLogin(@PathVariable String login) {
        return usuarioRepository.findBylogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado"));
    }

	
    
}
