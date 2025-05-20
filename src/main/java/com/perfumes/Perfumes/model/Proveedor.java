package com.perfumes.Perfumes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "proveedores")

public class Proveedor {

    private int id;
    private String nombre;
    private String contacto;
}
