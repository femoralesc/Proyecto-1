package com.perfumes.Perfumes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perfume {

    private int id;
    private String nombre;
    private String marca;
    private String tipo;
    private String genero;

}
