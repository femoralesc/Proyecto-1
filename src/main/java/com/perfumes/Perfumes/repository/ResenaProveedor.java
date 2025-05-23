package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResenaProveedor extends JpaRepository<Resena, Long> {
    @Query(value = "SELECT * FROM resenas WHERE idPedido= :idPedido", nativeQuery = true)
    Resena buscarresena(@org.springframework.lang.NonNull Long idPedido);
}
