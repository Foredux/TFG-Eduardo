package com.example.ejercicioApartado2punto7.security.errorhandling;

public class JwtTokenException extends RuntimeException{

    public JwtTokenException(String msg){
        super(msg);
    }

}
