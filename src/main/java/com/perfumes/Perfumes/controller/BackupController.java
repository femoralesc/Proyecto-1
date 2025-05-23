package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Backup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/backups")
public class BackupController {

    @Autowired
    private BackupService backupService;

    @GetMapping
    public ResponseEntity<List<Backup>> listar() {
        List<Backup> backups = backupService.findAll();
        if (backups.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(backups);
    }

    @PostMapping
    public ResponseEntity<Backup> crear(@RequestBody Backup backup) {
        Backup backupCreado = backupService.save(backup);
        return ResponseEntity.status(HttpStatus.CREATED).body(backupCreado);
    }
}