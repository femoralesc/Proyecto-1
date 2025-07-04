package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.assemblers.ClienteModelAssembler;
import com.perfumes.Perfumes.model.Cliente;
import com.perfumes.Perfumes.service.ClienteService;
import com.perfumes.Perfumes.service.PerfumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Cliente", description = "Operaciones relacionadas con clientes")
public class ClienteController {

    @Autowired
    public ClienteService clienteService;

    @Autowired
    ClienteModelAssembler clienteModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description = "Obtiene una lista de todos los clientes")
    public ResponseEntity<List<Cliente>> list() {
        List<Cliente> clientes = clienteService.findAll();
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente", description = "Agrega un nuevo cliente al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente clienteCreated = clienteService.save(cliente);
        return new ResponseEntity<>(clienteCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Busca un cliente según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Cliente> findById(
            @Parameter(description = "ID del cliente a buscar") @PathVariable Long id) {
        try {
            Cliente cliente = clienteService.findById(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente", description = "Modifica los datos de un cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Cliente> update(
            @Parameter(description = "ID del cliente a modificar") @PathVariable Long id,
            @RequestBody Cliente cliente) {
        try {
            Cliente cli = clienteService.findById(id);
            cli.setIdCliente(id);
            cli.setRutCliente(cliente.getRutCliente());
            cli.setNombreCliente(cliente.getNombreCliente());
            cli.setApellidoCliente(cliente.getApellidoCliente());
            cli.setDireccionCliente(cliente.getDireccionCliente());
            cli.setTelefonoCliente(cliente.getTelefonoCliente());
            cli.setDireccionCliente(cliente.getDireccionCliente());
            clienteService.save(cli);
            return new ResponseEntity<>(cli, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Cliente> delete(
            @Parameter(description = "ID del cliente a eliminar") @PathVariable Long id) {
        try {
            clienteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
