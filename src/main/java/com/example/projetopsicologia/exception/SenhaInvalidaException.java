package com.example.projetopsicologia.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(){
        super("Senha Invalida");
    }
}
