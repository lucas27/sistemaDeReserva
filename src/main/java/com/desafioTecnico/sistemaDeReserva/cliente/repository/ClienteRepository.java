package com.desafioTecnico.sistemaDeReserva.cliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafioTecnico.sistemaDeReserva.cliente.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Optional<Cliente> getReferenceByNome(String nome);

    Optional<Cliente> getByNome(String nome);

    boolean existsByEmail(String email);  
    
}