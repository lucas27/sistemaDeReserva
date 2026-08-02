package com.desafioTecnico.sistemaDeReserva.shared.handler.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"status", "message"})
@Schema(name = "Resposta de erro", description = "Payload para retorna o erro")
public class ExceptionMessageDto{
    private Integer status;
    private String message;
}
