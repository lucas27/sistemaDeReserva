package com.desafioTecnico.sistemaDeReserva.cliente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "Requisição dos dados do cliente", description = "Payload para registrar os dados do cliente")
public record ClienteRequestDto(
    @NotBlank(message = "Nome do cliente não pode ser vázio.")
    @Size(max=150)
    String nome,
    
    @NotBlank(message = "Email não pode está vázio")
    @Email(message = "Email deve ter um formato válido")
    @Size(max=150)
    String email,
    
    @NotBlank(message = "Telefone não pode está vázio")
    @Size(max=15)
    String telefone
) {}
