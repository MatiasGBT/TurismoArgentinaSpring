package com.mgbt.service;

import com.mgbt.dao.IAuditoriaDao;
import com.mgbt.domain.Auditoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoriaServiceImpl implements IService<Auditoria> {

    @Autowired
    IAuditoriaDao auditoriaDao;
    
    @Override
    public List<Auditoria> listar() {
        return auditoriaDao.findAll();
    }

    @Override
    public void guardar(Auditoria auditoria) {
        auditoriaDao.save(auditoria);
    }

    @Override
    public void eliminar(Auditoria auditoria) {
        auditoriaDao.delete(auditoria);
    }

    @Override
    public Auditoria encontrar(Auditoria auditoria) {
        return auditoriaDao.findById(auditoria.getIdAuditoria()).orElse(null);
    }

    public List<Auditoria> encontrarPorTipo(int tipo) {
        return auditoriaDao.findByTipo(tipo);
    }
    
}
