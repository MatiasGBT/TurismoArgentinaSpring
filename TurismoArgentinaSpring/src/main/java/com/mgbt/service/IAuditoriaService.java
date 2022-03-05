package com.mgbt.service;

import com.mgbt.domain.Auditoria;
import java.util.List;

public interface IAuditoriaService {
    
    public List<Auditoria> listarAuditorias();

    public void guardar(Auditoria auditoria);

    public void eliminar(Auditoria auditoria);

    public Auditoria encontrarAuditoria(Auditoria auditoria);
    
    public List<Auditoria> encontrarPorTipo(int tipo);
}
