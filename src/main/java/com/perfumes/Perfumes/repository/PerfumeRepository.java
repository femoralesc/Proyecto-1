package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {
}
