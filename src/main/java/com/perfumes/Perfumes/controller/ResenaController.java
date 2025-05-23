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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resenas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Resena> create(@RequestBody Resena resena) {
        Resena resenaCreated = resenaService.save(resena);
        return new ResponseEntity<>(resenaCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> findById(@PathVariable Long id) {
        try {
            Resena resena = resenaService.findById(id);
            return new ResponseEntity<>(resena, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resena> update(@PathVariable Long id, @RequestBody Resena resena) {
        try{
            Resena res = resenaService.findById(id);
            res.setIdResena(id);
            res.setPostResena(resena.getPostResena());
            res.setNotaResena(resena.getNotaResena());

            resenaService.save(res);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            resenaService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}