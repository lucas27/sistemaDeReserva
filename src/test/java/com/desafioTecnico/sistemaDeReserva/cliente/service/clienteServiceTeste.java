package com.desafioTecnico.sistemaDeReserva.cliente.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.desafioTecnico.sistemaDeReserva.cliente.domain.Cliente;
import com.desafioTecnico.sistemaDeReserva.cliente.dto.request.ClienteRequestDto;
import com.desafioTecnico.sistemaDeReserva.cliente.dto.response.ClienteResponseDto;
import com.desafioTecnico.sistemaDeReserva.cliente.repository.ClienteRepository;
import com.desafioTecnico.sistemaDeReserva.shared.validator.Validator;

@ExtendWith(MockitoExtension.class)
public class clienteServiceTeste {
    
    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @Mock
    private Validator validator;

    private Cliente cliente;

    // para usar dentro do método de teste
    private ClienteRequestDto requestDto;

    @BeforeEach
    public void adicionarDados() {
        requestDto = new ClienteRequestDto("João", "joão123@teste.com", "(91) 99999-8888"); 
        cliente = new Cliente();
        cliente.setNome(requestDto.nome());
        cliente.setEmail(requestDto.email());
        cliente.setTelefone(requestDto.telefone());

        when(repository.save(any(Cliente.class))).thenReturn(cliente);
    }
    
    @Test
    @DisplayName("deve adicionar no banco de dados os dados do cliente")
    public void adicionarClienteBancoDadosTesteSucesso() {

        service.salvarCliente(requestDto);

        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Test
    @DisplayName("deve retorna os dados do cliente")
    public void deveRetornaClienteTesteSucesso() {
        ClienteResponseDto response = service.salvarCliente(requestDto);
        
        assertNotNull(response);
        assertEquals("João", response.nome());
        assertEquals("joão123@teste.com", response.email());
        assertEquals("(91) 99999-8888", response.telefone());
    }
}
