package com.desafioTecnico.sistemaDeReserva.reserva.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.desafioTecnico.sistemaDeReserva.reserva.domain.Reserva;
import com.desafioTecnico.sistemaDeReserva.reserva.enums.ReservaEnum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonPropertyOrder({"reservaId", "sala", "endereco", "andar", "descricao", "reservaStatus", "dataInicial", "dataFinal", "horaInicial", "horaFinal"})
@Schema(name = "Resposta da realização da Reserva", description = "Payload da resposta da reserva")
public record RealizarReservaResponseDto(
    Long reservaId,
    String sala,
    String endereco,
    String andar,
    String descricao,
    ReservaEnum reservaStatus,
    LocalDate dataInicial,
    LocalDate dataFinal,
    LocalTime horaInicial,
    LocalTime horaFinal
) {
    public static RealizarReservaResponseDto pegarDadoRealizarReserva(Reserva reserva) {
        return new RealizarReservaResponseDto(
            reserva.getId(),
            reserva.getSala().getNome(),
            reserva.getSala().getLocalizacao().getEndereco(),
            reserva.getSala().getLocalizacao().getAndar(),
            reserva.getSala().getLocalizacao().getDescricao(),
            reserva.getReservaStatus(),
            reserva.getDataReserva(),
            reserva.getDataFinal(),
            reserva.getHoraReserva(),
            reserva.getHoraFinal()
        );
    }
}
