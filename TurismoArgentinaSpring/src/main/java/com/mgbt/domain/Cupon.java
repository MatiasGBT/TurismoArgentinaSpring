package com.mgbt.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cupon")
public class Cupon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cupon")
    private int idCupon;

    @NotEmpty(message = "El nombre del cupon no puede estar vacío")
    private String nombre;

    @Min(value = 5, message = "El descuento debe ser de al menos 5%")
    @Max(value = 100, message = "El descuento no puede superar el 100%")
    private int descuento;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fecha;
}
