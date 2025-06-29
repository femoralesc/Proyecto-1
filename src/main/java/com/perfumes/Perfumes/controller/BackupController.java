package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Backup;
import com.perfumes.Perfumes.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//push q me pidio el alfonso
@RestController
@RequestMapping("/api/v1/backups")
public class BackupController {

    @Autowired
    private BackupService backupService;

    @GetMapping
    public ResponseEntity<List<Backup>> list() {
        List<Backup> backups = backupService.findAll();
        if (backups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(backups, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Backup> create(@RequestBody Backup backup) {
        Backup backupCreated = backupService.save(backup);
        return new ResponseEntity<>(backupCreated, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Backup> update(@PathVariable Long id, @RequestBody Backup backup) {
        try{
            Backup bac = backupService.findById(id);
            bac.setIdBackup(id);
            bac.setFechaBackup(backup.getFechaBackup());
            bac.setArchivoBackup(backup.getArchivoBackup());
            backupService.save(bac);
            return new ResponseEntity<>(bac, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping
    public ResponseEntity<Backup> delete(@PathVariable Long id) {
        try{
            backupService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}