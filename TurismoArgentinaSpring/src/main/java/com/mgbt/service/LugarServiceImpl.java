package com.mgbt.service;

import com.mgbt.dao.ILugarDao;
import com.mgbt.domain.Lugar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LugarServiceImpl implements IService<Lugar> {

    @Autowired
    private ILugarDao lugarDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Lugar> listar() {
        return lugarDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Lugar lugar) {
        lugarDao.save(lugar);
    }

    @Override
    @Transactional
    public void eliminar(Lugar lugar) {
        lugarDao.delete(lugar);
    }

    @Override
    @Transactional(readOnly = true)
    public Lugar encontrar(Lugar lugar) {
        return lugarDao.findById(lugar.getIdLugar()).orElse(null);
    }
}
