package com.mgbt.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
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
    
    @NotEmpty(message = "El nombre de la actividad no puede estar vacío")
    private String nombre;
    
    private String imagen;
    
    @Min(value = 1, message = "El precio del lugar tiene que ser mayor a 0")
    private double precio;
}
