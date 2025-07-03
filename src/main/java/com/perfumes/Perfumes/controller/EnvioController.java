package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Envio;
import com.perfumes.Perfumes.service.EnvioService;
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
@RequestMapping("/api/v1/envios")
@Tag(name = "Envio", description = "Operaciones relacionadas con envíos")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    @Operation(summary = "Obtener todos los envíos", description = "Obtiene una lista de todos los envíos")
    public ResponseEntity<List<Envio>> list() {
        List<Envio> envios = envioService.findAll();
        if (envios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(envios, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo envío", description = "Agrega un nuevo envío al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Envío creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Envio> create(@RequestBody Envio envio) {
        Envio envioCreated = envioService.save(envio);
        return new ResponseEntity<>(envioCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar envío por ID", description = "Busca un envío según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío encontrado"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<Envio> findById(
            @Parameter(description = "ID del envío a buscar") @PathVariable Long id) {
        try {
            Envio envio = envioService.findById(id);
            return new ResponseEntity<>(envio, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un envío", description = "Modifica los datos de un envío existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<Envio> update(
            @Parameter(description = "ID del envío a modificar") @PathVariable Long id,
            @RequestBody Envio envio) {
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
    @Operation(summary = "Eliminar un envío", description = "Elimina un envío según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Envío eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<Envio> delete(
            @Parameter(description = "ID del envío a eliminar") @PathVariable Long id) {
        try {
            envioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
