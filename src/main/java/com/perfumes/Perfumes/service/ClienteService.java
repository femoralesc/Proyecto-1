package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional



public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public Iterable<com.perfumes.Perfumes.model.Cliente> findAll() {
        return clienteRepository.findAll();
    }

}
