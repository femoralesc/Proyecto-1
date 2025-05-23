package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface EnvioRepository extends JpaRepository<Envio, Long> {
    @Query(value = "SELECT * FROM envios WHERE estado= :estado", nativeQuery = true)
    Envio buscarenvio (@Param("estado") String estado);
}
