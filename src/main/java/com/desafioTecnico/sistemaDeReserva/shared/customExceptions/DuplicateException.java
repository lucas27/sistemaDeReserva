package com.desafioTecnico.sistemaDeReserva.shared.customExceptions;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String message) {
        super(message);
    }
}
