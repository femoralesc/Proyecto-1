package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Backup;
import com.perfumes.Perfumes.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupRepository extends JpaRepository<Backup, Long> {
}
