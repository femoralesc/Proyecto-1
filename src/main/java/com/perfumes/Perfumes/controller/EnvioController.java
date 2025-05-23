package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Envio;
import com.perfumes.Perfumes.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> listar() {
        List<Envio> envios = envioService.findAll();
        if (envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(envios);
    }

    @PostMapping
    public ResponseEntity<Envio> guardar(@RequestBody Envio envio) {
        Envio envioCreado = envioService.save(envio);
        return ResponseEntity.status(HttpStatus.CREATED).body(envioCreado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> buscar(@PathVariable Long id) {
        try {
            Envio envio = envioService.findById(id);
            return ResponseEntity.ok(envio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        try {
            Envio envio = envioService.findById(id);
            envio.setEstado(nuevoEstado);
            envioService.save(envio);
            return ResponseEntity.ok(envio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}