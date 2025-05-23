package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface ResenaRepository extends JpaRepository<Resena, Long> {
    @Query(value = "SELECT * FROM resenas WHERE notaResena= :notaResena", nativeQuery = true)
    Resena buscarresena(@Param("notaResena") String notaResena);
}
