package com.perfumes.Perfumes.repository;


import com.perfumes.Perfumes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "SELECT * FROM cliente WHERE rutCliente= :rutCliente", nativeQuery = true)
    Cliente buscarcliente (@Param("rutCliente") String rutCliente);
}
