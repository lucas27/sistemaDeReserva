package com.desafioTecnico.sistemaDeReserva.sala.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafioTecnico.sistemaDeReserva.sala.domain.Sala;
import com.desafioTecnico.sistemaDeReserva.sala.dto.response.SalaConsultaResponseDto;

import jakarta.persistence.LockModeType;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    Boolean existsByNome(String nome);

    Optional<Sala> getReferenceByNome(String nome);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT s FROM Sala s WHERE s.nome = :salaNome 
        """)
    Optional<Sala> findBySalaNomeWithLock(@Param("salaNome") String nome);

    @Query("""
        SELECT new com.desafioTecnico.sistemaDeReserva.sala.dto.response.SalaConsultaResponseDto(
            s.nome, 
            s.tipoSala, 
            l.endereco, 
            l.andar 
        )
        FROM Sala s 
        JOIN s.localizacao l 
        WHERE s.ativa = true 
        AND s.id NOT IN (
            SELECT r.sala.id FROM Reserva r
            WHERE r.sala.id IS NOT NULL
            AND r.reservaStatus = 'RESERVADA' 
            AND r.dataReserva <= :data 
            AND r.dataFinal >= :data
        )
    """)
    List<SalaConsultaResponseDto> pegarListaSalasLivres(
        @Param("data") LocalDate data 
    );

}