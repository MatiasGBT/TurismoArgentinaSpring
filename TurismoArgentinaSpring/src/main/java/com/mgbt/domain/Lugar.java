package com.mgbt.domain;

import java.io.Serializable;
import javax.persistence.*;
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

    private String nombre;
    private String descripcion;
    private String portada;
    private String foto1;
    private String foto2;
    private String foto3;
    private double precio;
}
