package com.perfumes.Perfumes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "backups")



public class Backup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String fecha;

    private String archivo;
}
