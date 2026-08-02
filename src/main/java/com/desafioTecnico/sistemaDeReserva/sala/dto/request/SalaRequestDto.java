package com.desafioTecnico.sistemaDeReserva.sala.dto.request;

import com.desafioTecnico.sistemaDeReserva.sala.enums.SalaEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "Requisição de criação de sala", description = "Payload para registrar a sala")
public record SalaRequestDto(
    @NotBlank(message = "O nome da sala é obrigatório")
    @Size(max = 100)
    String nome,
    
    @NotBlank(message = "A descrição da sala é obrigatório")
    String descricao,
    
    @NotNull(message = "O tipo de sala é obrigatório")
    @Schema(example = "COLETIVA ou INDIVIDUAL ou AUDITORIO" )
    SalaEnum tipoSala,

    @Valid
    LocalizacaoDto localizacao
) {}
