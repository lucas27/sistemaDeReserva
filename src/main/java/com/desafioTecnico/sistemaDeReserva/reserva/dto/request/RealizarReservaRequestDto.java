package com.desafioTecnico.sistemaDeReserva.reserva.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Requisição da realização da Reserva", description = "Payload da realização da reserva")
public record RealizarReservaRequestDto(
    @NotBlank(message = "Nome do cliente é obrigatório")
    String cliente,
    
    @NotBlank(message = "Nome da sala é obrigatório")
    String sala,

    @NotNull(message = "data da reserva é obrigatório")
    LocalDate dataReserva,
    
    LocalDate dataFinal,
    
    @NotNull(message = "hora de início da reserva é obrigatório")
    @Schema(example = "09:00")
    LocalTime horaReserva,
    
    @NotNull(message = "hora de final da reserva é obrigatório")
    @Schema(example = "15:00")
    LocalTime horaFinal
) {}
