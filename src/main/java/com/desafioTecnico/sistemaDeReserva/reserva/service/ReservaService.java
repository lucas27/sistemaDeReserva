package com.desafioTecnico.sistemaDeReserva.reserva.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafioTecnico.sistemaDeReserva.cliente.domain.Cliente;
import com.desafioTecnico.sistemaDeReserva.cliente.repository.ClienteRepository;
import com.desafioTecnico.sistemaDeReserva.reserva.domain.Reserva;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.request.AgendaReservadasRequestDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.request.CancelarReservaRequestDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.request.RealizarReservaRequestDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.response.AgendaReservadasResponseDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.response.CancelarReservaResponseDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.response.RealizarReservaResponseDto;
import com.desafioTecnico.sistemaDeReserva.reserva.enums.ReservaEnum;
import com.desafioTecnico.sistemaDeReserva.reserva.repository.ReservaRepository;
import com.desafioTecnico.sistemaDeReserva.sala.domain.Sala;
import com.desafioTecnico.sistemaDeReserva.sala.repository.SalaRepository;
import com.desafioTecnico.sistemaDeReserva.shared.validator.Validator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ClienteRepository clienteRepository;
    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;
    
    private final Validator validator;

    @Transactional
    public RealizarReservaResponseDto Reservar(RealizarReservaRequestDto dto) {
        // =================================================================================================================
        // evitar conflito de duas pessoas querendo reservar a mesma sala
        salaRepository.findBySalaNomeWithLock(dto.sala()).orElseThrow(() -> new EntityNotFoundException("Sala não encontrada"));
        
        // Pegar o id do cliente para adicionar na reserva
        Cliente cliente = clienteRepository.getReferenceByNome(dto.cliente()).orElseThrow(() -> new EntityNotFoundException("cliente não encontrado"));
        
        // Pegar o id da sala para adicionar na reserva
        Sala sala = salaRepository.getReferenceByNome(dto.sala()).orElseThrow(() -> new EntityNotFoundException("sala não encontrada"));

        // =================================================================================================================
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setSala(sala);
        reserva.setDataReserva(dto.dataReserva());
        reserva.setDataFinal(dto.dataFinal());
        reserva.setHoraReserva(dto.horaReserva());
        reserva.setHoraFinal(dto.horaFinal());
        reserva.setReservaStatus(ReservaEnum.RESERVADA);
        // ==================================================================================================================

        // Validar se já foi reservado
        validator.dataReservada(reserva);
        validator.horasIguais(reserva);   
        
        sala.setAtiva(false);
        salaRepository.save(sala);

        Reserva resposta = reservaRepository.save(reserva);        
        
        return RealizarReservaResponseDto.pegarDadoRealizarReserva(resposta);
    }

    @Transactional
    public CancelarReservaResponseDto cancelarReserva(CancelarReservaRequestDto dto) {
        Reserva reserva = reservaRepository.encontrarReservaParaCancelar(
            dto.reservaId(), dto.sala(), dto.dataReserva()
        ).orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada"));
        
        reserva.setReservaStatus(ReservaEnum.CANCELADA);

        reservaRepository.save(reserva);

        return CancelarReservaResponseDto.pegarDadoReservaCancelado(reserva);
    }

    @Transactional(readOnly = true)
    public List<AgendaReservadasResponseDto> consultarAgenda(AgendaReservadasRequestDto dto) {
        List<Reserva> reservas = reservaRepository.buscarReservas(dto.data());
        return reservas.stream().map(AgendaReservadasResponseDto::pegaConsultaData).toList();
    
    }

    
}