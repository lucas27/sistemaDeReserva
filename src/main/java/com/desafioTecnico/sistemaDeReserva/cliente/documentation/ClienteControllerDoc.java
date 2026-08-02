package com.desafioTecnico.sistemaDeReserva.cliente.documentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.desafioTecnico.sistemaDeReserva.cliente.dto.ClienteRequestDto;
import com.desafioTecnico.sistemaDeReserva.cliente.dto.ClienteResponseDto;
import com.desafioTecnico.sistemaDeReserva.shared.handler.dto.ExceptionMessageDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "cliente")
public interface ClienteControllerDoc {
    @Operation(summary = "adicionar dados do cliente", description = "Rota para adicionar os dados do cliente ao banco de dados")
    @ApiResponse(responseCode = "201", description = "cliente criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponseDto.class)))
    @ApiResponse(responseCode = "409", description = "E-mail já existe", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionMessageDto.class)))
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponseDto> adicionarCliente(@RequestBody @Valid ClienteRequestDto dto);       
}