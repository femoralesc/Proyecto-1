package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Permiso;
import com.perfumes.Perfumes.service.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permisos")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    @GetMapping
    public ResponseEntity<List<Permiso>> listar() {
        List<Permiso> permisos = permisoService.findAll();
        if (permisos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(permisos);
    }

    @PostMapping
    public ResponseEntity<Permiso> guardar(@RequestBody Permiso permiso) {
        Permiso permisoCreado = permisoService.save(permiso);
        return ResponseEntity.status(HttpStatus.CREATED).body(permisoCreado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permiso> buscar(@PathVariable Long id) {
        try {
            Permiso permiso = permisoService.findById(id);
            return ResponseEntity.ok(permiso);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}