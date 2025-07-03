package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Perfume;
import com.perfumes.Perfumes.service.PerfumeService;
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
@RequestMapping("/api/v1/perfumes")
@Tag(name = "Perfume", description = "Operaciones relacionadas con perfumes")
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    @GetMapping
    @Operation(summary = "Obtener todos los perfumes", description = "Obtiene una lista de todos los perfumes disponibles")
    public ResponseEntity<List<Perfume>> list() {
        List<Perfume> perfumes = perfumeService.findAll();
        if (perfumes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo perfume", description = "Agrega un nuevo perfume al catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Perfume creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Perfume> create(@RequestBody Perfume perfume) {
        Perfume perfumeCreated = perfumeService.save(perfume);
        return new ResponseEntity<>(perfumeCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un perfume por ID", description = "Busca un perfume en el sistema según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfume encontrado"),
            @ApiResponse(responseCode = "404", description = "Perfume no encontrado")
    })
    public ResponseEntity<Perfume> findById(
            @Parameter(description = "ID del perfume a buscar") @PathVariable Long id) {
        try {
            Perfume perfume = perfumeService.findById(id);
            return new ResponseEntity<>(perfume, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un perfume", description = "Modifica los datos de un perfume existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfume actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Perfume no encontrado")
    })
    public ResponseEntity<Perfume> update(
            @Parameter(description = "ID del perfume a actualizar") @PathVariable Long id,
            @RequestBody Perfume perfume) {
        try {
            Perfume per = perfumeService.findById(id);
            per.setIdPerfume(id);
            per.setNombrePerfume(perfume.getNombrePerfume());
            per.setMarcaPerfume(perfume.getMarcaPerfume());
            per.setTipoPerfume(perfume.getTipoPerfume());
            per.setGeneroPerfume(perfume.getGeneroPerfume());
            per.setStockPerfume(perfume.getStockPerfume());

            perfumeService.save(per);
            return new ResponseEntity<>(per, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un perfume", description = "Elimina un perfume existente del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Perfume eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Perfume no encontrado")
    })
    public ResponseEntity<Perfume> delete(
            @Parameter(description = "ID del perfume a eliminar") @PathVariable Long id) {
        try {
            perfumeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
