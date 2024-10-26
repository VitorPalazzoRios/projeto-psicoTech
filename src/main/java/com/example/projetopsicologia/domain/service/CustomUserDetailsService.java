package com.example.projetopsicologia.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.projetopsicologia.domain.entity.Usuario;
import com.example.projetopsicologia.domain.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Usuario> usuarioOptional = usuarioRepository.findBylogin(username);
    Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    
    return User.withUsername(usuario.getLogin())
               .password(passwordEncoder().encode(usuario.getSenha()))
               .roles("ADMIN")
               .build();

              
}

@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
}

}
