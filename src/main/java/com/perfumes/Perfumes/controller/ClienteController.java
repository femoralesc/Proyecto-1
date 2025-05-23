package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Cliente;
import com.perfumes.Perfumes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {


    @Autowired
    public ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> list() {
        List<Cliente> clientes = clienteService.findAll();
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
    Cliente clienteCreated = clienteService.save(cliente);
    return new ResponseEntity<>(clienteCreated, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.findById(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
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
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        try{
            clienteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


