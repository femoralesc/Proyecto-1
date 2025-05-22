package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Envio;
import com.perfumes.Perfumes.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> findAll() {
        return envioRepository.findAll();
    }

    public Envio findById(Long id) {
        return envioRepository.findById(id).get();
    }

    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }
}