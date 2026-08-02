package com.desafioTecnico.sistemaDeReserva.sala.dto.response;

import com.desafioTecnico.sistemaDeReserva.sala.domain.Localizacao;
import com.desafioTecnico.sistemaDeReserva.sala.domain.Sala;
import com.desafioTecnico.sistemaDeReserva.sala.enums.SalaEnum;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Resposta dos dados da sala", description = "Payload dos dados da sala")
public record SalaResponseDto(
    Long id,
    String nome,
    String salaDescricao,
    SalaEnum tipoSala,
    Boolean ativo,
    String endereco,
    String andar,
    String localizacaoDescricao
) {
    // uma forma de simplificar o json, evitando o set no retorno do service 
    public static SalaResponseDto pegarValorEntidade(Sala sala) {
        Localizacao local = sala.getLocalizacao();

        return new SalaResponseDto(
            sala.getId(), 
            sala.getNome(), 
            sala.getDescricao(), 
            sala.getTipoSala(), 
            sala.getAtiva(), 
            local.getEndereco(), 
            local.getAndar(), 
            local.getDescricao()
        );
    }
}
