package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    public Iterable<com.perfumes.Perfumes.model.Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

}
