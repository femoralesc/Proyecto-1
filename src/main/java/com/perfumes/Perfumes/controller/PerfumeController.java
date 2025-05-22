package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Perfume;
import com.perfumes.Perfumes.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
