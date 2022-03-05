package com.mgbt.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "actividad")
public class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_actividad")
    private int idActividad;
    
    private String nombre;
    private String imagen;
    private double precio;
}
