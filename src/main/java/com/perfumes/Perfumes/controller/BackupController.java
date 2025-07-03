package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Backup;
import com.perfumes.Perfumes.service.BackupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/backups")
@Tag(name = "Backup", description = "Operaciones relacionadas con backup")
public class BackupController {

    @Autowired
    private BackupService backupService;

    @GetMapping
    @Operation(summary = "Obtener todos los backups", description = "Obtiene una lista de todos los backups")
    public ResponseEntity<List<Backup>> list() {
        List<Backup> backups = backupService.findAll();
        if (backups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(backups, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo backup", description = "Agrega un nuevo backup al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Backup creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Backup> create(@RequestBody Backup backup) {
        Backup backupCreated = backupService.save(backup);
        return new ResponseEntity<>(backupCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un backup existente", description = "Modifica los datos de un backup existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Backup actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Backup no encontrado")
    })
    public ResponseEntity<Backup> update(
            @Parameter(description = "ID del backup a modificar") @PathVariable Long id,
            @RequestBody Backup backup) {
        try {
            Backup existing = backupService.findById(id);
            existing.setFechaBackup(backup.getFechaBackup());
            existing.setArchivoBackup(backup.getArchivoBackup());
            Backup updated = backupService.save(existing);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un backup", description = "Elimina un backup según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Backup eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Backup no encontrado")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID del backup a eliminar") @PathVariable Long id) {
        try {
            backupService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
