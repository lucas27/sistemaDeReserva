package com.desafioTecnico.sistemaDeReserva.reserva.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
import com.desafioTecnico.sistemaDeReserva.sala.domain.Localizacao;
import com.desafioTecnico.sistemaDeReserva.sala.domain.Sala;
import com.desafioTecnico.sistemaDeReserva.sala.enums.SalaEnum;
import com.desafioTecnico.sistemaDeReserva.sala.repository.SalaRepository;
import com.desafioTecnico.sistemaDeReserva.shared.validator.Validator;

@ExtendWith(MockitoExtension.class)
public class reservaServiceTeste {
    
    @Mock
    private ReservaRepository repository;
    
    @Mock
    private SalaRepository salaRepository;
    
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ReservaService service;
    
    @Mock
    private Validator validator;
    
    private Reserva reserva;

    private Cliente cliente;

    private Sala sala;

    private Localizacao localizacao;

    private RealizarReservaRequestDto requestDto;
    
    private CancelarReservaRequestDto cancelarReservaRequestDto;

    private AgendaReservadasRequestDto agendaReservadasRequestDto;

    @BeforeEach
    public void carregarDados() {

        // adicionar os dados dentro das entidades
        cliente = new Cliente(1L, "vitor", "vitor123@teste.com", "(91) 98888-7777", LocalDateTime.now(), LocalDateTime.now());
        localizacao = new Localizacao(1L, "Av.senador lemos", "4º Andar", "Em frente à recepção principal", LocalDateTime.now());
        sala = new Sala(1L, localizacao, "Sala Omega", "Sala de reunião", SalaEnum.COLETIVA, true, LocalDateTime.now(), LocalDateTime.now());

        requestDto = new RealizarReservaRequestDto(cliente.getNome(), sala.getNome(), LocalDate.of(2026, 8, 5), LocalDate.of(2026, 8, 6), LocalTime.of(8, 0, 0), LocalTime.of(14, 0, 0));

        // =================================================================================================================
        
        // iniciar a instancia da entidade reserva
        reserva = new Reserva();
        
        reserva.setId(1L);
        reserva.setCliente(cliente);
        reserva.setSala(sala);
        reserva.setDataReserva(requestDto.dataReserva());
        reserva.setDataFinal(requestDto.dataFinal());
        reserva.setHoraReserva(requestDto.horaReserva());
        reserva.setHoraFinal(requestDto.horaFinal());
        reserva.setReservaStatus(ReservaEnum.RESERVADA);
        
        // =================================================================================================================
        
        // adicionar dto
        cancelarReservaRequestDto = new CancelarReservaRequestDto(reserva.getId(), reserva.getSala().getNome(), LocalDate.of(2026, 8, 10));
        agendaReservadasRequestDto = new AgendaReservadasRequestDto(LocalDate.of(2026, 8, 5));
    }

    @Test
    @DisplayName("deve adicionar realizar reserva no banco de dados")
    public void adicionarRealizarReservaBancoDadosComSucesso() {
        when(salaRepository.findBySalaNomeWithLock(sala.getNome())).thenReturn(Optional.of(sala));
        when(clienteRepository.getReferenceByNome(cliente.getNome())).thenReturn(Optional.of(cliente));
        when(salaRepository.getReferenceByNome(sala.getNome())).thenReturn(Optional.of(sala));
        when(repository.save(any(Reserva.class))).thenReturn(reserva);

        service.Reservar(requestDto);

        // validações
        verify(repository, times(1)).save(any(Reserva.class));
    }

    @Test
    @DisplayName("deve retorna os dados do realizar reserva")
    public void deveRetornaDadosReservaComSucesso() {
        when(salaRepository.findBySalaNomeWithLock(sala.getNome())).thenReturn(Optional.of(sala));
        when(clienteRepository.getReferenceByNome(cliente.getNome())).thenReturn(Optional.of(cliente));
        when(salaRepository.getReferenceByNome(sala.getNome())).thenReturn(Optional.of(sala));
        when(repository.save(any(Reserva.class))).thenReturn(reserva);

        RealizarReservaResponseDto responseDto = service.Reservar(requestDto);

        // validações
        assertNotNull(responseDto);
        assertEquals(1L, responseDto.reservaId());
        assertEquals("Sala Omega", responseDto.sala());
        assertEquals("Av.senador lemos", responseDto.endereco());
        assertEquals("4º Andar", responseDto.andar());
        assertEquals("Em frente à recepção principal", responseDto.descricao());
        assertEquals(ReservaEnum.RESERVADA, responseDto.reservaStatus());
        assertEquals(LocalDate.of(2026, 8, 5), responseDto.dataInicial());
        assertEquals(LocalDate.of(2026, 8, 6), responseDto.dataFinal());
        assertEquals(LocalTime.of(8, 0, 0), responseDto.horaInicial());
        assertEquals(LocalTime.of(14, 0, 0), responseDto.horaFinal());
    }
    
    @Test
    @DisplayName("deve cancelar reserva e pegar dados no banco de dados reserva cancelada")
    public void cancelarReservaComSucesso() {
        // adicionado na data 2026-08-10
        when(repository.encontrarReservaParaCancelar(reserva.getId(), sala.getNome(), LocalDate.of(2026, 8, 10))).thenReturn(Optional.of(reserva));

        reserva.setReservaStatus(ReservaEnum.CANCELADA);

        when(repository.save(any(Reserva.class))).thenReturn(reserva);

        CancelarReservaResponseDto responseDto = service.cancelarReserva(cancelarReservaRequestDto);

        
        // verificar se foi salvo com reservaStatus "cancelada"
        verify(repository, times(1)).save(any(Reserva.class));
        
        assertNotNull(responseDto);
        assertEquals(1L, responseDto.reservaId());
        assertEquals("Sala Omega", responseDto.sala());
        assertEquals("Av.senador lemos", responseDto.endereco());
        assertEquals(ReservaEnum.CANCELADA, responseDto.reservaStatus());
        assertEquals(LocalDate.of(2026, 8, 5), responseDto.dataInicial());
        assertEquals(LocalDate.of(2026, 8, 6), responseDto.dataFinal());
        assertEquals(LocalTime.of(8, 0, 0), responseDto.horaInicial());
        assertEquals(LocalTime.of(14, 0, 0), responseDto.horaFinal());
    }

    @Test
    @DisplayName("deve consultar reserva e pegar dados no banco de dados")
    public void consultarReservaComSucesso() {
        List<Reserva> listaReservasMock = List.of(reserva);

        when(repository.buscarReservas(agendaReservadasRequestDto.data())).thenReturn(listaReservasMock);

        List<AgendaReservadasResponseDto> responseDto = service.consultarAgenda(agendaReservadasRequestDto);

        // validações
        assertNotNull(responseDto);
        assertEquals(1, responseDto.size());
        
        verify(repository, times(1)).buscarReservas(agendaReservadasRequestDto.data());
        
        assertEquals(1L, responseDto.get(0).ReservaId());
        assertEquals("Sala Omega", responseDto.get(0).sala());
        assertEquals(LocalDate.of(2026, 8, 5), responseDto.get(0).dataInicio());
        assertEquals(LocalDate.of(2026, 8, 6), responseDto.get(0).dataFim());
        assertEquals(LocalTime.of(8, 0, 0), responseDto.get(0).horaInicio());
        assertEquals(LocalTime.of(14, 0, 0), responseDto.get(0).horaFim());
    }
}
