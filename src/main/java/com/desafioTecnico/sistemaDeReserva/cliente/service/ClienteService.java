package com.desafioTecnico.sistemaDeReserva.cliente.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafioTecnico.sistemaDeReserva.cliente.domain.Cliente;
import com.desafioTecnico.sistemaDeReserva.cliente.dto.request.ClienteRequestDto;
import com.desafioTecnico.sistemaDeReserva.cliente.dto.response.ClienteResponseDto;
import com.desafioTecnico.sistemaDeReserva.cliente.repository.ClienteRepository;
import com.desafioTecnico.sistemaDeReserva.shared.validator.Validator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;
    private final Validator validator;

    @Transactional
    public ClienteResponseDto salvarCliente(ClienteRequestDto dto) {
        // forma de segurança para não quebrar a api e o banco de dados
        // validar se já existe o email dentro do banco de dados
        // e acionar o handler
        validator.validarEmailDuplicado(dto.email());
        
        Cliente cliente = new Cliente();
        
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        
        Cliente salvo = repository.save(cliente);
        
        ClienteResponseDto responseDto = new ClienteResponseDto(
            salvo.getId(), 
            salvo.getNome(), 
            salvo.getEmail(), 
            salvo.getTelefone()
        );
        return responseDto;
    }
}