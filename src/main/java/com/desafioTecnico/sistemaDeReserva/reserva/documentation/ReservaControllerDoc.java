package com.desafioTecnico.sistemaDeReserva.reserva.documentation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.desafioTecnico.sistemaDeReserva.reserva.dto.request.AgendaReservadasRequestDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.request.CancelarReservaRequestDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.request.RealizarReservaRequestDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.response.AgendaReservadasResponseDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.response.CancelarReservaResponseDto;
import com.desafioTecnico.sistemaDeReserva.reserva.dto.response.RealizarReservaResponseDto;
import com.desafioTecnico.sistemaDeReserva.shared.handler.dto.ExceptionMessageDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name="Reserva")
public interface ReservaControllerDoc {
    // ============================================== Realizar Reserva =============================================================
    @Operation(summary = "Realizar reserva", description = "Rota para realizar reserva")
    @ApiResponse(responseCode = "201", description = "reserva criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RealizarReservaResponseDto.class)))
    @ApiResponse(responseCode = "409", description = "Reserva já existe", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @PostMapping("/reservas")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RealizarReservaResponseDto> realizarReserva(@RequestBody @Valid RealizarReservaRequestDto dto); 
    // ============================================================================================================================

    // ============================================== Cancelar Reserva =============================================================
    @Operation(summary = "Cancelar reserva", description = "Rota para cancelar reserva")
    @ApiResponse(responseCode = "200", description = "reserva cancelada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CancelarReservaResponseDto.class)))
    @ApiResponse(responseCode = "409", description = "Reserva já existe", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @PutMapping("/cancelar/reservas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CancelarReservaResponseDto> cancelarReserva(@RequestBody @Valid CancelarReservaRequestDto dto); 
    // ============================================================================================================================
   
    // ============================================== consulta Reserva =============================================================
    @Operation(summary = "Consulta reserva", description = "Rota para consulta de reserva")
    @ApiResponse(responseCode = "200", description = "reservas encontradas com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgendaReservadasResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @PostMapping("/consultar/reservas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AgendaReservadasResponseDto>> consultaReserva(@RequestBody @Valid AgendaReservadasRequestDto dto); 
    // ============================================================================================================================
    
}