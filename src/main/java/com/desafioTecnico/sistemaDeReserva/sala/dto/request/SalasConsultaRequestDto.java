package com.desafioTecnico.sistemaDeReserva.sala.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Requisição de consulta de salas", description = "Payload para consular salas livres")
public record SalasConsultaRequestDto(
    @NotNull(message = "A data é obrigatória")
    LocalDate data
) {}
