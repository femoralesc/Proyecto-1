package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Proveedor;
import com.perfumes.Perfumes.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> listar() {
        List<Proveedor> proveedores = proveedorService.findAll();
        if (proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }

    @PostMapping
    public ResponseEntity<Proveedor> guardar(@RequestBody Proveedor proveedor) {
        Proveedor proveedorCreado = proveedorService.save(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorCreado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> buscar(@PathVariable Long id) {
        try {
            Proveedor proveedor = proveedorService.findById(id);
            return ResponseEntity.ok(proveedor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}