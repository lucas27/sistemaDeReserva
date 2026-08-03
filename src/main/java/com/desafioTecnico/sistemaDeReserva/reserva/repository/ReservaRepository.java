package com.desafioTecnico.sistemaDeReserva.reserva.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafioTecnico.sistemaDeReserva.reserva.domain.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END 
        FROM Reserva r
        WHERE r.sala.id = :salaId 
        AND r.reservaStatus = 'RESERVADA'
        AND r.dataReserva <= :dataFinal
        AND r.dataFinal >= :dataInicial
        AND r.horaReserva <= :horaFinal
        AND r.horaFinal >= :horaInicial
    """)
    Boolean existeUmaReservaParaDiaHora(
        @Param("salaId") Long id,
        @Param("dataInicial") LocalDate dataInicial,
        @Param("dataFinal") LocalDate dataFinal,
        @Param("horaInicial") LocalTime horaInicial,
        @Param("horaFinal") LocalTime horaFinal
    );


    @Query("""
        SELECT r FROM Reserva r
        WHERE r.reservaStatus = 'RESERVADA' 
        AND r.id = :reservaId
        AND r.sala.nome = :sala
        AND r.dataReserva = :data
        """)
    Optional<Reserva> encontrarReservaParaCancelar(
        @Param("reservaId") Long id,
        @Param("sala") String sala,
        @Param("data") LocalDate dataReserva
    );

    @Query("""
        SELECT r FROM Reserva r
        JOIN FETCH r.sala s
        WHERE r.reservaStatus = 'RESERVADA'
        AND r.dataReserva <= :data
        AND r.dataFinal >= :data
        ORDER BY s.nome ASC, r.horaReserva ASC
    """)
    List<Reserva> buscarReservas(@Param("data") LocalDate data);
}