package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
    @Query(value = "SELECT * FROM envios WHERE estado= :estado", nativeQuery = true)
    Envio buscarenvio(@org.springframework.lang.NonNull String estado);
}
