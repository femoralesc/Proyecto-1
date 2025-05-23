package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Backup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BackupRepository extends JpaRepository<Backup, Long> {
    @Query(value = "SELECT * FROM backup WHERE nombre= :nombre", nativeQuery = true)
    Backup buscarbackup(@org.springframework.lang.NonNull String nombre);
}
