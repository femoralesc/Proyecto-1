package com.perfumes.Perfumes.service;


import com.perfumes.Perfumes.repository.BackupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class BackupService {
    @Autowired
    private BackupRepository backupRepository;

}
