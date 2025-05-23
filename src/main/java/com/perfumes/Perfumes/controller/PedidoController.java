package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Pedido;
import com.perfumes.Perfumes.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/pedidos")

public class PedidoController {


    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidos = pedidoService.findAll();
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
        Pedido pedidoCreated = pedidoService.save(pedido);
        return new ResponseEntity<>(pedidoCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        try{
            Pedido pedido = pedidoService.findById(id);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedido) {
        try{
            Pedido ped = pedidoService.findById(id);
            ped.setIdPedido(pedido.getIdPedido());
            ped.setRutPedido(pedido.getRutPedido());
            ped.setNombrePedido(pedido.getNombrePedido());
            ped.setIdPedido(pedido.getIdPedido());

            pedidoService.save(ped);
            return new ResponseEntity<>(ped, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try{
            pedidoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
