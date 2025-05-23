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
    public ResponseEntity<List<Envio>> list() {
        List<Envio> envios = envioService.findAll();
        if (envios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(envios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Envio> create(@RequestBody Envio envio) {
        Envio envioCreated = envioService.save(envio);
        return new ResponseEntity<>(envioCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> findById(@PathVariable Long id) {
        try {
            Envio envio = envioService.findById(id);
            return new ResponseEntity<>(envio, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> update(@PathVariable Long id, @RequestBody Envio envio) {
        try {
            Envio env = envioService.findById(id);
            env.setIdEnvio(envio.getIdEnvio());
            env.setEstadoEnvio(envio.getEstadoEnvio());
            env.setDireccionEnvio(envio.getDireccionEnvio());
            env.setFechaEnvio(envio.getFechaEnvio());

            envioService.save(envio);
            return new ResponseEntity<>(envio, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Envio> delete(@PathVariable Long id) {
        try{
            envioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}