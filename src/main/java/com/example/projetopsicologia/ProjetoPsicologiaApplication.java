package com.example.projetopsicologia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.projetopsicologia.security.JwtUtil;

@SpringBootApplication
public class ProjetoPsicologiaApplication implements CommandLineRunner {

    @Autowired 
    private JwtUtil jwtUtil;

    public static void main(String[] args) {
        SpringApplication.run(ProjetoPsicologiaApplication.class, args);
    }

 @Override public void run(String... args) throws Exception { 
    // Exemplo de como gerar um token padrão 
    String token = jwtUtil.generateToken("username", "USER"); 
    System.out.println("Generated Token: " + token); 
    // Você pode armazenar esse token em algum lugar acessível globalmente, se necessário

    }
}
