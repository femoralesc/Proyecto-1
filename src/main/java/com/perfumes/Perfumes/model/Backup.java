package com.perfumes.Perfumes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    private int id;
    private String fecha;
    private String archivo;
}
