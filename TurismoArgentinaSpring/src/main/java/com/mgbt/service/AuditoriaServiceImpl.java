package com.mgbt.service;

import com.mgbt.dao.IAuditoriaDao;
import com.mgbt.domain.Auditoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditoriaServiceImpl implements IService<Auditoria> {

    @Autowired
    IAuditoriaDao auditoriaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Auditoria> listar() {
        return auditoriaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Auditoria auditoria) {
        auditoriaDao.save(auditoria);
    }

    @Override
    @Transactional
    public void eliminar(Auditoria auditoria) {
        auditoriaDao.delete(auditoria);
    }

    @Override
    @Transactional(readOnly = true)
    public Auditoria encontrar(Auditoria auditoria) {
        return auditoriaDao.findById(auditoria.getIdAuditoria()).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Auditoria> encontrarPorTipo(int tipo) {
        return auditoriaDao.findByTipo(tipo);
    }
    
}
