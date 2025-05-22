package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Perfume;
import com.perfumes.Perfumes.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}
