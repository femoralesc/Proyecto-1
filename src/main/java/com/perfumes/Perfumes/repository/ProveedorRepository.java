package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    @Query(value = "SELECT * FROM proveedor WHERE nombreProveedor= :nombreProveedor", nativeQuery = true)
    Proveedor buscarproveedor(@org.springframework.lang.NonNull String nombreProveedor);
}
