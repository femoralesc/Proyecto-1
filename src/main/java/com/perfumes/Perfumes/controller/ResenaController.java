package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Resena;
import com.perfumes.Perfumes.service.ResenaService;
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
@RequestMapping("/api/v1/resenas")
@Tag(name = "Reseña", description = "Operaciones relacionadas con las reseñas de perfumes")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    @Operation(summary = "Obtener todas las reseñas", description = "Obtiene una lista de todas las reseñas registradas")
    public ResponseEntity<List<Resena>> listar() {
        List<Resena> resenas = resenaService.findAll();
        if (resenas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resenas, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva reseña", description = "Agrega una nueva reseña al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reseña creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Resena> create(@RequestBody Resena resena) {
        Resena resenaCreated = resenaService.save(resena);
        return new ResponseEntity<>(resenaCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reseña por ID", description = "Obtiene una reseña específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña encontrada"),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada")
    })
    public ResponseEntity<Resena> findById(
            @Parameter(description = "ID de la reseña a buscar") @PathVariable Long id) {
        try {
            Resena resena = resenaService.findById(id);
            return new ResponseEntity<>(resena, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una reseña", description = "Modifica los datos de una reseña existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada")
    })
    public ResponseEntity<Resena> update(
            @Parameter(description = "ID de la reseña a actualizar") @PathVariable Long id,
            @RequestBody Resena resena) {
        try {
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
    @Operation(summary = "Eliminar una reseña", description = "Elimina una reseña específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada")
    })
    public ResponseEntity<?> delete(
            @Parameter(description = "ID de la reseña a eliminar") @PathVariable Long id) {
        try {
            resenaService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
