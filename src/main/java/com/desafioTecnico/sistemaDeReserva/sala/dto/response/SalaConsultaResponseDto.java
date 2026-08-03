package com.desafioTecnico.sistemaDeReserva.sala.dto.response;

import com.desafioTecnico.sistemaDeReserva.sala.enums.SalaEnum;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Resposta da consulta de salas livres", description = "Payload para consular salas livres em um determinado dia")
public record SalaConsultaResponseDto(
    String nome,
    SalaEnum tipoSala,
    String endereco,
    String andar
) {}
