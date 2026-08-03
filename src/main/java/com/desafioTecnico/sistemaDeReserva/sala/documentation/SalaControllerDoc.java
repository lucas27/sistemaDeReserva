package com.desafioTecnico.sistemaDeReserva.sala.documentation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.desafioTecnico.sistemaDeReserva.sala.dto.request.SalasConsultaRequestDto;
import com.desafioTecnico.sistemaDeReserva.sala.dto.request.SalaRequestDto;
import com.desafioTecnico.sistemaDeReserva.sala.dto.response.SalaConsultaResponseDto;
import com.desafioTecnico.sistemaDeReserva.sala.dto.response.SalaResponseDto;
import com.desafioTecnico.sistemaDeReserva.shared.handler.dto.ExceptionMessageDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Sala")
public interface SalaControllerDoc { 
    // ============================================== Adicionar Sala ==============================================================
    @Operation(summary = "adicionar sala", description = "Rota para adicionar os dados da sala ao banco de dados")
    @ApiResponse(responseCode = "201", description = "Sala criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SalaResponseDto.class)))
    @ApiResponse(responseCode = "409", description = "Sala já existe", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @PostMapping("/salas")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SalaResponseDto> adicionarSala(@RequestBody @Valid SalaRequestDto dto); 
    // ============================================================================================================================
    
    // ============================================== Consultar Salas ==============================================================
    @Operation(summary = "Buscar salas Livres", description = "Rota para buscar os dados de salas livres no banco de dados")
    @ApiResponse(responseCode = "200", description = "Salas encontradas com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SalaConsultaResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @PostMapping("/consultar/salas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<SalaConsultaResponseDto>> consultarSalas(@RequestBody @Valid SalasConsultaRequestDto dto); 
    // =============================================================================================================================
}