package com.desafioTecnico.sistemaDeReserva.cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioTecnico.sistemaDeReserva.cliente.documentation.ClienteControllerDoc;
import com.desafioTecnico.sistemaDeReserva.cliente.dto.request.ClienteRequestDto;
import com.desafioTecnico.sistemaDeReserva.cliente.dto.response.ClienteResponseDto;
import com.desafioTecnico.sistemaDeReserva.cliente.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController implements ClienteControllerDoc{ 
    private final ClienteService service;

    ClienteController(ClienteService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ClienteResponseDto> adicionarCliente(ClienteRequestDto dto) {
        ClienteResponseDto resposta = service.salvarCliente(dto); 
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }
    
}