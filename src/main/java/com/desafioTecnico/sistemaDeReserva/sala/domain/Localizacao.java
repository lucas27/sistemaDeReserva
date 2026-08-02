package com.desafioTecnico.sistemaDeReserva.sala.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "localizacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String endereco;

    @Column(length = 50, nullable = false)
    private String andar;
    
    @Column(length = 255)
    private String descricao;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
