package com.mgbt.service;

import com.mgbt.dao.IUsoDao;
import com.mgbt.domain.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsoServiceImpl implements IService<Uso> {

    @Autowired
    IUsoDao usoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Uso> listar() {
        return usoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Uso uso) {
        usoDao.save(uso);
    }

    @Override
    @Transactional
    public void eliminar(Uso uso) {
        usoDao.delete(uso);
    }

    @Override
    @Transactional(readOnly = true)
    public Uso encontrar(Uso uso) {
        return usoDao.findById(uso.getIdUso()).orElse(null);
    }
    
    @Transactional(readOnly = true)
    public Uso encontrarPorUsuarioYCupon(Usuario usuario, Cupon cupon) {
        return usoDao.findByUsuarioAndCupon(usuario, cupon);
    }
    
    @Transactional
    public void eliminarPorUsuario(Usuario usuario) {
        usoDao.deleteByUsuario(usuario);
    }
}
