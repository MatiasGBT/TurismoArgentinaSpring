package com.mgbt.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "auditoria")
public class Auditoria implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_auditoria")
    private int idAuditoria;
    
    private String accion;
    private String usuario;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fecha;
    
    private int tipo;
}
