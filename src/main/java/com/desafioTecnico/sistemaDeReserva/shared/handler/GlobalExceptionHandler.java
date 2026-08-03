package com.desafioTecnico.sistemaDeReserva.shared.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.desafioTecnico.sistemaDeReserva.shared.customExceptions.DuplicateException;
import com.desafioTecnico.sistemaDeReserva.shared.handler.dto.ExceptionMessageDto;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // status 409
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ExceptionMessageDto> emailDuplicado(DuplicateException exception) {
        ExceptionMessageDto dto = new ExceptionMessageDto();

        dto.setStatus(HttpStatus.CONFLICT.value());
        dto.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionMessageDto> naoFoiEncontrado(EntityNotFoundException exception) {
        ExceptionMessageDto dto = new ExceptionMessageDto();

        dto.setStatus(HttpStatus.NOT_FOUND.value());
        dto.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }

    // status 400 
    @ExceptionHandler(HttpMessageNotReadableException.class) 
    public ResponseEntity<ExceptionMessageDto> erroEnum(HttpMessageNotReadableException exception) {
        ExceptionMessageDto dto = new ExceptionMessageDto();

        dto.setStatus(HttpStatus.BAD_REQUEST.value());
        dto.setMessage("apenas INDIVIDUAL, AUDITORIO ou COLETIVA");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    // status 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMessageDto> erroValidacaoPayload(MethodArgumentNotValidException exception) {
        String mensagemErro = exception.getBindingResult()
            .getAllErrors()
            .get(0)
            .getDefaultMessage();
        
        ExceptionMessageDto dto = new ExceptionMessageDto();
        dto.setStatus(HttpStatus.BAD_REQUEST.value());
        dto.setMessage(mensagemErro);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    // status 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessageDto> handleGenericException(Exception exception) {

        ExceptionMessageDto dto = new ExceptionMessageDto();
        dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dto.setMessage("Erro interno no servidor.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
    }
}
