package com.mgbt.domain;

import java.io.Serializable;
import javax.persistence.*;
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
    
    private String nombre;
    private String email;
    private String comentario;
}