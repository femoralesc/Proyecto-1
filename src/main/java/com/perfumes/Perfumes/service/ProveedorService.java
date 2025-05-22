package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Proveedor;
import com.perfumes.Perfumes.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    public Proveedor findById(Long id) {
        return proveedorRepository.findById(id).get();
    }

    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }
}