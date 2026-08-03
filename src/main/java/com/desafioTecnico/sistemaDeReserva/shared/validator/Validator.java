package com.desafioTecnico.sistemaDeReserva.shared.validator;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.desafioTecnico.sistemaDeReserva.cliente.repository.ClienteRepository;
import com.desafioTecnico.sistemaDeReserva.reserva.domain.Reserva;
import com.desafioTecnico.sistemaDeReserva.reserva.repository.ReservaRepository;
import com.desafioTecnico.sistemaDeReserva.sala.repository.SalaRepository;
import com.desafioTecnico.sistemaDeReserva.shared.customExceptions.DuplicateException;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class Validator {
    private final ClienteRepository clienteRepository;
    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    @Transactional(readOnly = true)
    public void validarEmailDuplicado(String email) {
        boolean emailDuplicado = clienteRepository.existsByEmail(email);
        if(emailDuplicado) {
            throw new DuplicateException("E-mail já existe");
        }
    }

    @Transactional(readOnly = true)
    public void salaDuplicada(String nome) {
        boolean salaDuplicada = salaRepository.existsByNome(nome);

        if(salaDuplicada) {
            throw new DuplicateException("Sala já existe");
        }
    }

    @Transactional(readOnly = true)
    public void dataReservada(Reserva reserva) {
        boolean reservado = reservaRepository.existeUmaReservaParaDiaHora(
            reserva.getSala().getId(),
            reserva.getDataReserva(), 
            reserva.getDataFinal(), 
            reserva.getHoraReserva(), 
            reserva.getHoraFinal()
        );
       
        if(reservado) {
            throw new DuplicateException("Sala já foi reservada");
        }
    }

    public void horasIguais(Reserva reserva) {
       boolean reservado = reserva.getHoraReserva().equals(reserva.getHoraFinal());

        if(reservado) {
            throw new DuplicateException("Hora da reserva iguais");
        }
    }
}
