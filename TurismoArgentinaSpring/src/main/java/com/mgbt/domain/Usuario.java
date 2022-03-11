package com.mgbt.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private int idUsuario;
    
    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    private String username;
    
    @NotEmpty(message = "El email no puede estar vacío")
    @Email(message = "El formato de email no es válido")
    private String email;
    
    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String username, String email, String password, Rol rol) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
}