package com.desafioTecnico.sistemaDeReserva.sala.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafioTecnico.sistemaDeReserva.sala.domain.Localizacao;
import com.desafioTecnico.sistemaDeReserva.sala.domain.Sala;
import com.desafioTecnico.sistemaDeReserva.sala.dto.request.SalaRequestDto;
import com.desafioTecnico.sistemaDeReserva.sala.dto.request.SalasConsultaDto;
import com.desafioTecnico.sistemaDeReserva.sala.dto.response.SalaResponseDto;
import com.desafioTecnico.sistemaDeReserva.sala.repository.SalaRepository;
import com.desafioTecnico.sistemaDeReserva.sala.validator.SalaValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaService {
    private final SalaRepository repository;
    private final SalaValidator validator;
    
    @Transactional
    public SalaResponseDto adicionarSala(SalaRequestDto dto) {
        // =========== tratamento de erro =============
        validator.salaDuplicada(dto.nome());
        // ============================================

        //========== entidade localização ==============
        Localizacao local = new Localizacao();
        local.setEndereco(dto.localizacao().endereco());
        local.setAndar(dto.localizacao().andar());
        local.setDescricao(dto.localizacao().descricao());
        // ==============================================


        //============== entidade sala ================== 
        Sala sala = new Sala();
        sala.setNome(dto.nome());
        sala.setDescricao(dto.descricao());
        sala.setTipoSala(dto.tipoSala());   
        sala.setAtiva(true);
        sala.setLocalizacao(local);
        // ============================================

        // salvar no banco de dados e pegar o valor para mandar no retorno como resposta
        Sala response = repository.save(sala);
        
        return SalaResponseDto.pegarValorEntidade(response);
    }

    public List<SalaResponseDto> consultarSalasLivres(SalasConsultaDto dto) {
        List<SalaResponseDto> salasLivre = repository.pegarListaSalasLivres(dto.nome(), dto.endereco());
        return salasLivre;

    }
}