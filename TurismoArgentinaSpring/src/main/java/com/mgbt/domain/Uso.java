package com.mgbt.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "uso")
public class Uso implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_uso")
    private int idUso;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_cupon", referencedColumnName = "id_cupon")
    private Cupon cupon;
    
    @Column(columnDefinition = "BOOLEAN")
    private boolean usado;
}
