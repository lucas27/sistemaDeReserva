package com.desafioTecnico.sistemaDeReserva.sala.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.desafioTecnico.sistemaDeReserva.sala.documentation.SalaControllerDoc;
import com.desafioTecnico.sistemaDeReserva.sala.dto.request.SalaRequestDto;
import com.desafioTecnico.sistemaDeReserva.sala.dto.request.SalasConsultaDto;
import com.desafioTecnico.sistemaDeReserva.sala.dto.response.SalaResponseDto;
import com.desafioTecnico.sistemaDeReserva.sala.service.SalaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController("/sala")
@RequiredArgsConstructor
public class SalaController implements SalaControllerDoc {

    private final SalaService service;

    @Override
    public ResponseEntity<SalaResponseDto> adicionarSala(SalaRequestDto dto) {
        SalaResponseDto resposta = service.adicionarSala(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @Override
    public ResponseEntity<List<SalaResponseDto>> consultarSalas(@Valid SalasConsultaDto dto) {
       List<SalaResponseDto> resposta = service.consultarSalasLivres(dto);
       return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
    
}