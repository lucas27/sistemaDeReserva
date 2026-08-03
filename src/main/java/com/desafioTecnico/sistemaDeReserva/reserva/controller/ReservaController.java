package com.desafioTecnico.sistemaDeReserva.reserva.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioTecnico.sistemaDeReserva.reserva.documentation.ReservaControllerDoc;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.request.AgendaReservadasRequestDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.request.CancelarReservaRequestDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.request.RealizarReservaRequestDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.response.AgendaReservadasResponseDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.response.CancelarReservaResponseDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.response.RealizarReservaResponseDto;
import com.desafioTecnico.sistemaDeReserva.reserva.service.ReservaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/reserva")
public class ReservaController implements ReservaControllerDoc{
    private final ReservaService service;

    ReservaController(ReservaService service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<RealizarReservaResponseDto> realizarReserva(RealizarReservaRequestDto dto) {
        RealizarReservaResponseDto resposta = service.Reservar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @Override
    public ResponseEntity<CancelarReservaResponseDto> cancelarReserva(CancelarReservaRequestDto dto) {
        CancelarReservaResponseDto resposta = service.cancelarReserva(dto);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }


    @Override
    public ResponseEntity<List<AgendaReservadasResponseDto>> consultaReserva(@Valid AgendaReservadasRequestDto dto) {
        List<AgendaReservadasResponseDto> resposta = service.consultarAgenda(dto);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    } 

    
}