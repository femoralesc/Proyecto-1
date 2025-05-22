package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

    @Query(value = "SELECT * FROM sucursal WHERE direccionSucursal= :direccionSucursal", nativeQuery = true)
    Sucursal buscarsucursal(@Param("direccionSucursal") String direccionSucursal);

}
