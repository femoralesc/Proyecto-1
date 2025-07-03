package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Proveedor;
import com.perfumes.Perfumes.service.ProveedorService;
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
@RequestMapping("/api/v1/proveedores")
@Tag(name = "Proveedor", description = "Operaciones relacionadas con proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Obtener todos los proveedores", description = "Obtiene una lista de todos los proveedores registrados")
    public ResponseEntity<List<Proveedor>> list() {
        List<Proveedor> proveedores = proveedorService.findAll();
        if (proveedores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo proveedor", description = "Agrega un nuevo proveedor al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proveedor creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Proveedor> create(@RequestBody Proveedor proveedor) {
        Proveedor proveedorCreated = proveedorService.save(proveedor);
        return new ResponseEntity<>(proveedorCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un proveedor por ID", description = "Obtiene un proveedor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<Proveedor> findById(
            @Parameter(description = "ID del proveedor a buscar") @PathVariable Long id) {
        try {
            Proveedor pro = proveedorService.findById(id);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proveedor", description = "Modifica los datos de un proveedor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<Proveedor> update(
            @Parameter(description = "ID del proveedor a actualizar") @PathVariable Long id,
            @RequestBody Proveedor proveedor) {
        try {
            Proveedor pro = proveedorService.findById(id);
            pro.setIdProveedor(id);
            pro.setNombreProveedor(proveedor.getNombreProveedor());
            pro.setMarcaProveedor(proveedor.getMarcaProveedor());
            pro.setContactoProveedor(proveedor.getContactoProveedor());

            proveedorService.save(pro);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proveedor", description = "Elimina un proveedor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proveedor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<Proveedor> delete(
            @Parameter(description = "ID del proveedor a eliminar") @PathVariable Long id) {
        try {
            proveedorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
