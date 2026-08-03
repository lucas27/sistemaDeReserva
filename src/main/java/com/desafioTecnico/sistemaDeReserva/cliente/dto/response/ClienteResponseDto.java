package com.desafioTecnico.sistemaDeReserva.cliente.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Resposta dos dados do cliente", description = "Payload para retorna os dados do cliente")
@JsonPropertyOrder({"id","nome","email","telefone"})
public record ClienteResponseDto(
    Long id,
    String nome,
    String email,
    String telefone

) {}
