package com.example.projetopsicologia.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(){
        super("Login invalido");
    }
}
