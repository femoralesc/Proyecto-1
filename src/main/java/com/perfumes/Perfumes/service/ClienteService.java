package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Cliente;
import com.perfumes.Perfumes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).get();
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}