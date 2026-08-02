package com.desafioTecnico.sistemaDeReserva.sala.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "Dto para localização da sala", description = "Payload para localização da sala")
public record LocalizacaoDto(
    @NotBlank(message = "O endereço da sala é obrigatório")
    @Size(max = 255)
    String endereco,
    
    @NotBlank(message = "O andar da sala é obrigatório")
    @Size(max = 50)
    String andar,
    
    @NotBlank(message = "A descrição da localização da sala é obrigatório")
    @Size(max = 255)
    String descricao
) {}
