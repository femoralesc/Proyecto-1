package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Proveedor;
import com.perfumes.Perfumes.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> list() {
        List<Proveedor> proveedores = proveedorService.findAll();
        if (proveedores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Proveedor> create(@RequestBody Proveedor proveedor) {
        Proveedor proveedorCreated = proveedorService.save(proveedor);
        return new ResponseEntity<>(proveedorCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> update(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        try{
            Proveedor pro = proveedorService.findById(id);
            pro.setIdProveedor(id);
            pro.setNombreProveedor(proveedor.getNombreProveedor());
            pro.setMarcaProveedor(proveedor.getMarcaProveedor());
            pro.setContactoProveedor(proveedor.getContactoProveedor());

            proveedorService.save(pro);
            return new ResponseEntity<>(pro, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Proveedor> delete(@PathVariable Long id) {
        try{
            proveedorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
}