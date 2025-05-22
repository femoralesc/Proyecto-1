package com.perfumes.Perfumes.repository;

import com.perfumes.Perfumes.model.Cliente;
import com.perfumes.Perfumes.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
