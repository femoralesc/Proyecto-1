package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.repository.PermisoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;
    public Iterable<com.perfumes.Perfumes.model.Permiso> findAll() {
        return permisoRepository.findAll();
    }


}

