package com.desafioTecnico.sistemaDeReserva.sala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafioTecnico.sistemaDeReserva.sala.domain.Sala;
import com.desafioTecnico.sistemaDeReserva.sala.dto.response.SalaResponseDto;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    Boolean existsByNome(String nome);

    //JPQL Foi a melhor forma de buscar
    @Query("SELECT new com.desafioTecnico.sistemaDeReserva.sala.dto.response.SalaResponseDto(" +
       "s.id, s.nome, s.descricao, s.tipoSala, s.ativa, l.endereco, l.andar, l.descricao) " +
       "FROM Sala s LEFT JOIN s.localizacao l " +
       "WHERE s.ativa = true " + 
        "AND ((:nome IS NOT NULL AND :nome != '' AND s.nome LIKE CONCAT('%', :nome, '%')) " +
       "  OR (:endereco IS NOT NULL AND :endereco != '' AND l.endereco LIKE CONCAT('%', :endereco, '%')))")
    List<SalaResponseDto> pegarListaSalasLivres(@Param("nome") String nome, @Param("endereco") String endereco);
    
}