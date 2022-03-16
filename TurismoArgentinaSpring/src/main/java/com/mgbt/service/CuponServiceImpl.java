package com.mgbt.service;

import com.mgbt.dao.ICuponDao;
import com.mgbt.domain.Cupon;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuponServiceImpl implements IService<Cupon> {

    @Autowired
    ICuponDao cuponDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Cupon> listar() {
        return cuponDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Cupon cupon) {
        cuponDao.save(cupon);
    }

    @Override
    @Transactional
    public void eliminar(Cupon cupon) {
        cuponDao.delete(cupon);
    }

    @Override
    @Transactional(readOnly = true)
    public Cupon encontrar(Cupon cupon) {
        return cuponDao.findById(cupon.getIdCupon()).orElse(null);
    }
    
    @Transactional(readOnly = true)
    public Cupon encontrarPorNombre(String nombre) {
        return cuponDao.findByNombre(nombre);
    }
}
