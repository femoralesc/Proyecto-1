package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    @Query(value = "SELECT * FROM permisos WHERE nombre= :nombre", nativeQuery = true)
    Permiso buscarpermiso(@org.springframework.lang.NonNull String nombre);
}
