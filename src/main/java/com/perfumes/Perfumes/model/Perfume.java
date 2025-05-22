package com.perfumes.Perfumes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perfume")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfume;

    @Column(unique = true, nullable = false)
    private String nombrePerfume;

    @Column(nullable = false)
    private String marcaPerfume;

    @Column(nullable = false)
    private String tipoPerfume;

    @Column(nullable = true)
    private String generoPerfume;

    @Column(nullable = false)
    private int stockPerfume;

}
