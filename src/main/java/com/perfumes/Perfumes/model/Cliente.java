package com.perfumes.Perfumes.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(length = 10, unique = true, nullable = false)
    private String rutCliente;

    @Column(unique = true, nullable = false)
    private String nombreCliente;

    @Column(nullable = false)
    private String apellidoCliente;

    @Column(nullable = false)
    private String direccionCliente;

    @Column(nullable = false)
    private String telefonoCliente;

    @Column(nullable = false)
    private String emailCliente;

}
