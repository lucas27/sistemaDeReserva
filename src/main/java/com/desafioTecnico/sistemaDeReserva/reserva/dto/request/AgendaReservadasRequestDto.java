package com.desafioTecnico.sistemaDeReserva.reserva.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Requisição de consulta de agenda", description = "Payload de consulta por data")
public record AgendaReservadasRequestDto(
    @NotNull(message = "data é obrigatória")
    LocalDate data
) {}
