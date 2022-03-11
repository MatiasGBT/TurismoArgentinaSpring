package com.mgbt.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lugar")
public class Lugar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_lugar")
    private int idLugar;

    @NotEmpty(message = "El nombre del lugar no puede estar vacío")
    private String nombre;
    
    @NotEmpty(message = "La descripción del lugar no puede estar vacío")
    private String descripcion;
    private String portada;
    private String foto1;
    private String foto2;
    private String foto3;
    
    @NotNull(message = "El precio del lugar no puede estar vacío")
    private double precio;
}
