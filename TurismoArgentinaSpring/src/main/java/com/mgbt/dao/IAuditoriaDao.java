package com.mgbt.dao;

import com.mgbt.domain.Auditoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuditoriaDao extends JpaRepository<Auditoria, Integer> {
    List<Auditoria> findByTipo(int i);
    
    List<Auditoria> findByTipoAndUsuario(int i, String usuario);
}
