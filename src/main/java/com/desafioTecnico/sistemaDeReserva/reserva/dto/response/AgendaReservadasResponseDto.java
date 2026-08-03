package com.desafioTecnico.sistemaDeReserva.reserva.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.desafioTecnico.sistemaDeReserva.reserva.domain.Reserva;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Resposta de consulta de agenda", description = "Payload de resposta da consulta por data")
public record AgendaReservadasResponseDto(
    Long ReservaId,
    String sala,
    LocalDate dataInicio,
    LocalDate dataFim,
    LocalTime horaInicio,
    LocalTime horaFim
) {
    @JsonPropertyOrder({"reservaId", "sala", "dataInicio", "dataFim", "horaInicio", "horaFim"})
    public static AgendaReservadasResponseDto pegaConsultaData(Reserva reserva) {
        return new AgendaReservadasResponseDto(
            reserva.getId(),
            reserva.getSala().getNome(),
            reserva.getDataReserva(),
            reserva.getDataFinal(),
            reserva.getHoraReserva(),
            reserva.getHoraFinal()
        );
    }
} 
