package com.perfumes.Perfumes.repository;


import com.perfumes.Perfumes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query(value = "SELECT * FROM cliente WHERE rutCliente= :rutCliente", nativeQuery = true)
    Cliente buscarcliente(@org.springframework.lang.NonNull String rutCliente);
}
