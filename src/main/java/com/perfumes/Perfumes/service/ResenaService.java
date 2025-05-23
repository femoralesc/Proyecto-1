package com.perfumes.Perfumes.service;

import com.perfumes.Perfumes.model.Resena;
import com.perfumes.Perfumes.repository.ResenaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public List<Resena> findAll() {

        return resenaRepository.findAll();
    }

    public Resena findById(Long id) {

        return resenaRepository.findById(id).get();
    }

    public Resena save(Resena resena) {

        return resenaRepository.save(resena);
    }

    public void delete(Long id) {

        resenaRepository.deleteById(id);
    }
}