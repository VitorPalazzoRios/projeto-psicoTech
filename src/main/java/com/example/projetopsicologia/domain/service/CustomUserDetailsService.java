package com.example.projetopsicologia.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.projetopsicologia.domain.entity.Usuario;
import com.example.projetopsicologia.domain.repository.UsuarioRepository;
import com.example.projetopsicologia.exception.SenhaInvalidaException;

import org.springframework.security.core.userdetails.User;

@Configuration
@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches( usuario.getSenha(), user.getPassword() );

        if(senhasBatem){
            return user;
        }
        
        throw new SenhaInvalidaException();
    }
    
   
   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Usuario> usuarioOptional = usuarioRepository.findBylogin(username);
    Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    
    return User.withUsername(usuario.getLogin())
               .password(usuario.getSenha())
               .roles("ADMIN")
               .build();

              
}


}
