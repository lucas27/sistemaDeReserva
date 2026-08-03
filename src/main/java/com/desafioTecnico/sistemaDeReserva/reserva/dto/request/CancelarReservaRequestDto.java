package com.desafioTecnico.sistemaDeReserva.reserva.dto.request;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Requisição do cancelamento da reserva", description = "Payload para cancelar a reserva")
public record CancelarReservaRequestDto(
    @NotNull(message = "número da reserva é obrigatório")
    Long reservaId,

    @NotBlank(message = "Nome da sala é obrigatório")
    String sala,
    
    @NotNull(message = "data da reserva é obrigatório")
    LocalDate dataReserva
) {}
