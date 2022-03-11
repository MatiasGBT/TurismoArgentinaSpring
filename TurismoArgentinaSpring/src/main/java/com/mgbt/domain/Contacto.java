package com.mgbt.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "contacto")
public class Contacto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_contacto")
    private int idContacto;
    
    @NotEmpty(message = "El campo nombre no puede estar vacío")
    private String nombre;
    
    @NotEmpty(message = "El campo email no puede estar vacío")
    private String email;
    
    @NotEmpty(message = "El campo comentario no puede estar vacío")
    private String comentario;
}