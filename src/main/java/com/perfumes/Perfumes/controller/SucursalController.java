package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Sucursal;
import com.perfumes.Perfumes.service.SucursalService;
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
@RequestMapping("api/v1/sucursales")
@Tag(name = "Sucursal", description = "Operaciones relacionadas con sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    @Operation(summary = "Obtener todas las sucursales", description = "Obtiene una lista de todas las sucursales registradas")
    public ResponseEntity<List<Sucursal>> list() {
        List<Sucursal> sucursales = sucursalService.findAll();
        if (sucursales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sucursales, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva sucursal", description = "Agrega una nueva sucursal al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Sucursal> create(@RequestBody Sucursal sucursal) {
        Sucursal sucursalCreated = sucursalService.save(sucursal);
        return new ResponseEntity<>(sucursalCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una sucursal", description = "Modifica los datos de una sucursal existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    public ResponseEntity<Sucursal> update(
            @Parameter(description = "ID de la sucursal a modificar") @PathVariable Long id,
            @RequestBody Sucursal sucursal) {
        try {
            Sucursal suc = sucursalService.findById(id);
            suc.setIdSucursal(id);
            suc.setDireccionSucursal(sucursal.getDireccionSucursal());
            suc.setCiudadSucursal(sucursal.getCiudadSucursal());
            suc.setTelefonoSucursal(sucursal.getTelefonoSucursal());
            suc.setAperturaSucursal(sucursal.getAperturaSucursal());
            suc.setCierreSucursal(sucursal.getCierreSucursal());

            sucursalService.save(suc);
            return new ResponseEntity<>(suc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una sucursal", description = "Elimina una sucursal por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucursal eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    public ResponseEntity<Sucursal> delete(
            @Parameter(description = "ID de la sucursal a eliminar") @PathVariable Long id) {
        try {
            sucursalService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
