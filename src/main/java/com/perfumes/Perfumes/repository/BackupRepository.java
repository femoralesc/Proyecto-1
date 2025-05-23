package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Backup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface BackupRepository extends JpaRepository<Backup, Long> {
    @Query(value = "SELECT * FROM backup WHERE nombre= :nombre", nativeQuery = true)
    Backup buscarbackup(@Param("nombre") String nombre);


}
