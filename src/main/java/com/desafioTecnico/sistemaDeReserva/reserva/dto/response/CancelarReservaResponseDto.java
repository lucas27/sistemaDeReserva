package com.desafioTecnico.sistemaDeReserva.reserva.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.desafioTecnico.sistemaDeReserva.reserva.domain.Reserva;
import com.desafioTecnico.sistemaDeReserva.reserva.enums.ReservaEnum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonPropertyOrder({"reservaId", "sala", "endereco", "reservaStatus", "dataInicial", "dataFinal", "horaInicial", "horaFinal"})
@Schema(name = "Resposta de cancelamento da Reserva", description = "Payload da resposta do cancelamento da reserva")
public record CancelarReservaResponseDto(
    Long reservaId,
    String sala,
    String endereco,
    ReservaEnum reservaStatus,
    LocalDate dataInicial,
    LocalDate dataFinal,
    LocalTime horaInicial,
    LocalTime horaFinal

) {
    public static CancelarReservaResponseDto pegarDadoReservaCancelado(Reserva reserva){
       return new CancelarReservaResponseDto(
        reserva.getId(),
        reserva.getSala().getNome(), 
        reserva.getSala().getLocalizacao().getEndereco(),
        reserva.getReservaStatus(),
        reserva.getDataReserva(),
        reserva.getDataFinal(),
        reserva.getHoraReserva(),
        reserva.getHoraFinal()
        );
    }
}
