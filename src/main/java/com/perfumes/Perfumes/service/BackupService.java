package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Backup;
import com.perfumes.Perfumes.repository.BackupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional

public class BackupService {
    @Autowired
    private BackupRepository backupRepository;

    public List<Backup> findAll() {
        return backupRepository.findAll();
    }

    public Backup findById(Long id) {
        return backupRepository.findById(id).get();

    }

    public Backup save(Backup backup) {
        return backupRepository.save(backup);
    }

    public void delete(Long id) {
        backupRepository.deleteById(id);
    }

}
