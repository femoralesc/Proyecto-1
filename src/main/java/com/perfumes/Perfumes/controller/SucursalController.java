package com.perfumes.Perfumes.controller;

import com.perfumes.Perfumes.model.Sucursal;
import com.perfumes.Perfumes.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1/sucursales")

public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> list() {
        List<Sucursal> sucursales = sucursalService.findAll();
        if (sucursales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sucursales, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Sucursal> create(@RequestBody Sucursal sucursal) {
        Sucursal sucursalCreated = sucursalService.save(sucursal);
        return new ResponseEntity<>(sucursalCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> update(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        try{
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
    public ResponseEntity<Sucursal> delete(@PathVariable Long id) {
        try {
            sucursalService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
