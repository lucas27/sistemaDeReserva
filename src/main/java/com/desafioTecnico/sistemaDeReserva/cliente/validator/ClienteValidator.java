package com.desafioTecnico.sistemaDeReserva.cliente.validator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.desafioTecnico.sistemaDeReserva.cliente.repository.ClienteRepository;
import com.desafioTecnico.sistemaDeReserva.shared.customExceptions.DuplicateException;


@Component
public class ClienteValidator {
    private final ClienteRepository repository;

    ClienteValidator(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public void validarEmailDuplicado(String email) {
        boolean emailDuplicado = repository.existsByEmail(email);
        if(emailDuplicado) {
            throw new DuplicateException("E-mail já existe");
        }
    }
}
