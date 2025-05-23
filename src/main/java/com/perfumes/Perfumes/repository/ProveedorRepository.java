package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository


public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    @Query(value = "SELECT * FROM proveedor WHERE nombreProveedor= :nombreProveedor", nativeQuery = true)
    Proveedor buscarproveedor(@Param("nombreProveedor") String nombreProveedor);
}
