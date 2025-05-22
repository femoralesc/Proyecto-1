package com.perfumes.Perfumes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sucursal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSucursal;

    @Column(unique = true, nullable = false)
    private String direccionSucursal;

    @Column(nullable = false)
    private String ciudadSucursal;

    @Column(nullable = false)
    private String telefonoSucursal;

    @Column(nullable = false)
    private String aperturaSucursal;

    @Column(nullable = false)
    private String cierreSucursal;


}
