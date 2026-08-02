package com.desafioTecnico.sistemaDeReserva.sala.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Requisição de consulta de salas", description = "Payload para consular salas livres")
public record SalasConsultaDto(
    String nome,
    String endereco
) {}
