package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Pedido;
import com.perfumes.Perfumes.service.PedidoService;
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
@RequestMapping("api/v1/pedidos")
@Tag(name = "Pedido", description = "Operaciones relacionadas con pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Obtener todos los pedidos", description = "Obtiene una lista de todos los pedidos registrados")
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidos = pedidoService.findAll();
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo pedido", description = "Agrega un nuevo pedido al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
        Pedido pedidoCreated = pedidoService.save(pedido);
        return new ResponseEntity<>(pedidoCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un pedido por ID", description = "Busca un pedido existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Pedido> findById(
            @Parameter(description = "ID del pedido a buscar") @PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.findById(id);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pedido", description = "Modifica los datos de un pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Pedido> update(
            @Parameter(description = "ID del pedido a actualizar") @PathVariable Long id,
            @RequestBody Pedido pedido) {
        try {
            Pedido ped = pedidoService.findById(id);
            ped.setIdPedido(pedido.getIdPedido());
            ped.setRutPedido(pedido.getRutPedido());
            ped.setNombrePedido(pedido.getNombrePedido());
            ped.setIdPedido(pedido.getIdPedido());

            pedidoService.save(ped);
            return new ResponseEntity<>(ped, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pedido", description = "Elimina un pedido existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<HttpStatus> delete(
            @Parameter(description = "ID del pedido a eliminar") @PathVariable Long id) {
        try {
            pedidoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
