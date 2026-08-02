package com.desafioTecnico.sistemaDeReserva.sala.validator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.desafioTecnico.sistemaDeReserva.sala.repository.SalaRepository;
import com.desafioTecnico.sistemaDeReserva.shared.customExceptions.DuplicateException;

@Component
public class SalaValidator {
    private final SalaRepository repository;

    SalaValidator(SalaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public void salaDuplicada(String nome) {
        boolean estaDuplicado = repository.existsByNome(nome);

        if(estaDuplicado) {
            throw new DuplicateException("Sala já existe");
        }
    }
}
