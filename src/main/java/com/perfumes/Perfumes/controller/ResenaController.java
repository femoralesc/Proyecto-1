package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Resena;
import com.perfumes.Perfumes.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<Resena>> listar() {
        List<Resena> resenas = resenaService.findAll();
        if (resenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resenas);
    }

    @PostMapping
    public ResponseEntity<Resena> guardar(@RequestBody Resena resena) {
        Resena resenaCreada = resenaService.save(resena);
        return ResponseEntity.status(HttpStatus.CREATED).body(resenaCreada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> buscar(@PathVariable Long id) {
        try {
            Resena resena = resenaService.findById(id);
            return ResponseEntity.ok(resena);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            resenaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}