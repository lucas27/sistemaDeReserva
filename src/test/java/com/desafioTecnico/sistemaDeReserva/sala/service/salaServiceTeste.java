package com.desafioTecnico.sistemaDeReserva.sala.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.desafioTecnico.sistemaDeReserva.sala.domain.Localizacao;
import com.desafioTecnico.sistemaDeReserva.sala.domain.Sala;
import com.desafioTecnico.sistemaDeReserva.sala.dto.request.LocalizacaoDto;
import com.desafioTecnico.sistemaDeReserva.sala.dto.request.SalaRequestDto;
import com.desafioTecnico.sistemaDeReserva.sala.dto.request.SalasConsultaRequestDto;
import com.desafioTecnico.sistemaDeReserva.sala.dto.response.SalaConsultaResponseDto;
import com.desafioTecnico.sistemaDeReserva.sala.dto.response.SalaResponseDto;
import com.desafioTecnico.sistemaDeReserva.sala.enums.SalaEnum;
import com.desafioTecnico.sistemaDeReserva.sala.repository.SalaRepository;
import com.desafioTecnico.sistemaDeReserva.shared.validator.Validator;

@ExtendWith(MockitoExtension.class)
public class salaServiceTeste {

    @Mock
    private SalaRepository repository;
    
    @Mock
    private Validator validator;

    @InjectMocks
    private SalaService service;

    private Sala sala;

    private Localizacao localizacao;

    private SalaRequestDto requestDto;

    private LocalizacaoDto localizacaoDto;

    private SalasConsultaRequestDto consultaRequestDto;

    @BeforeEach
    public void adicionarDto() {
        localizacaoDto = new LocalizacaoDto("Av.senador lemos", "4º Andar", "Em frente à recepção principal");
        requestDto = new SalaRequestDto("Sala Omega", "Sala de reunião", SalaEnum.COLETIVA, localizacaoDto);
        consultaRequestDto = new SalasConsultaRequestDto(LocalDate.of(2026, 8, 10));

        // entidade localização
        localizacao = new Localizacao();
        localizacao.setEndereco(localizacaoDto.endereco());
        localizacao.setAndar(localizacaoDto.andar());
        localizacao.setDescricao(localizacaoDto.descricao());

        // entidade sala
        sala = new Sala();
        sala.setId(1L);
        sala.setNome(requestDto.nome());
        sala.setDescricao(requestDto.descricao());
        sala.setTipoSala(requestDto.tipoSala());
        sala.setAtiva(true);
        sala.setLocalizacao(localizacao);
    }
    
    @Test
    @DisplayName("deve adicionar no banco de dados os dados da sala")
    public void adicionadoSalaBancoDadosComSucesso() {
        when(repository.save(any(Sala.class))).thenReturn(sala);
        
        service.adicionarSala(requestDto);
        
        verify(repository, times(1)).save(any(Sala.class));
    }
    
    @Test
    @DisplayName("deve retorna os dados da sala adicionado")
    public void deveRetornaSalaAdicionadaComSucesso() {
        when(repository.save(any(Sala.class))).thenReturn(sala);

        SalaResponseDto responseDto = service.adicionarSala(requestDto);
        
        assertNotNull(responseDto);
        assertEquals( 1L, responseDto.id());
        assertEquals("Sala Omega" ,responseDto.nome());
        assertEquals("Sala de reunião", responseDto.salaDescricao());
        assertEquals(true, responseDto.ativo());
        assertEquals(SalaEnum.COLETIVA, responseDto.tipoSala());
        assertEquals("Av.senador lemos", responseDto.endereco());
        assertEquals("4º Andar", responseDto.andar());
        assertEquals("Em frente à recepção principal", responseDto.localizacaoDescricao());
    }

    @Test
    @DisplayName("deve consulta no banco de dados os dados da sala")
    public void consultarSalasBancoDadosComSucesso() {
        SalaConsultaResponseDto sala1 = new SalaConsultaResponseDto("Sala Alpha", SalaEnum.COLETIVA, "Av.senador lemos", "4º Andar");
        SalaConsultaResponseDto sala2 = new SalaConsultaResponseDto("Sala Gamma", SalaEnum.AUDITORIO, "Av. generalissimo deodoro", "8º Andar");

        List<SalaConsultaResponseDto> listaMock = List.of(sala1, sala2);

        when(repository.pegarListaSalasLivres(consultaRequestDto.data())).thenReturn(listaMock);
        
        service.consultarSalasLivres(consultaRequestDto);
        
        verify(repository, times(1)).pegarListaSalasLivres(any(LocalDate.class));
    }
    
    @Test
    @DisplayName("deve retorna os dados de consulta da sala")
    public void deveRetornaConsultarSalasComSucesso() {
        SalaConsultaResponseDto sala1 = new SalaConsultaResponseDto("Sala Alpha", SalaEnum.COLETIVA, "Av. senador lemos", "4º Andar");
        SalaConsultaResponseDto sala2 = new SalaConsultaResponseDto("Sala Gamma", SalaEnum.AUDITORIO, "Av. generalissimo deodoro", "8º Andar");

        List<SalaConsultaResponseDto> listaMock = List.of(sala1, sala2);

        when(repository.pegarListaSalasLivres(consultaRequestDto.data())).thenReturn(listaMock);
        
        List<SalaConsultaResponseDto> responseDto = service.consultarSalasLivres(consultaRequestDto);
        
        assertNotNull(responseDto);
        assertEquals(2, responseDto.size());

        // verificar a primeira sala
        assertEquals("Sala Alpha" ,responseDto.get(0).nome());
        assertEquals(SalaEnum.COLETIVA ,responseDto.get(0).tipoSala());
        assertEquals("Av. senador lemos" ,responseDto.get(0).endereco());
        assertEquals("4º Andar" ,responseDto.get(0).andar());
       
        // verificar a segunda sala
        assertEquals("Sala Gamma" ,responseDto.get(1).nome());
        assertEquals(SalaEnum.AUDITORIO ,responseDto.get(1).tipoSala());
        assertEquals("Av. generalissimo deodoro" ,responseDto.get(1).endereco());
        assertEquals("8º Andar" ,responseDto.get(1).andar());
    }
}
