package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Perfume;
import com.perfumes.Perfumes.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/perfumes")



public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    @GetMapping
    public ResponseEntity<List<Perfume>> list() {
        List<Perfume> perfumes = perfumeService.findAll();
        if (perfumes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Perfume> create(@RequestBody Perfume perfume) {
        Perfume perfumeCreated = perfumeService.save(perfume);
        return new ResponseEntity<>(perfumeCreated, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfume> findById(@PathVariable Long id) {
        try {
            Perfume perfume = perfumeService.findById(id);
            return new ResponseEntity<>(perfume, HttpStatus.OK);

        } catch ( Exception e ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfume> update(@PathVariable Long id, @RequestBody Perfume perfume) {
        try{
            Perfume per = perfumeService.findById(id);
            per.setIdPerfume(id);
            per.setNombrePerfume(perfume.getNombrePerfume());
            per.setMarcaPerfume(perfume.getMarcaPerfume());
            per.setTipoPerfume(perfume.getTipoPerfume());
            per.setGeneroPerfume(perfume.getGeneroPerfume());
            per.setStockPerfume(perfume.getStockPerfume());

            perfumeService.save(per);
            return new ResponseEntity<>(per, HttpStatus.OK);

        } catch ( Exception e ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Perfume> delete(@PathVariable Long id) {
        try {
            perfumeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch ( Exception e ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
