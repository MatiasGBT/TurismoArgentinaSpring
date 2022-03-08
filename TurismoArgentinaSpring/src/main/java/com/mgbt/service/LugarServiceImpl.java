package com.mgbt.service;

import com.mgbt.dao.ILugarDao;
import com.mgbt.domain.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LugarServiceImpl implements IService<Lugar> {

    @Autowired
    private ILugarDao lugarDao;
    
    @Override
    public List<Lugar> listar() {
        return lugarDao.findAll();
    }

    @Override
    public void guardar(Lugar lugar) {
        lugarDao.save(lugar);
    }

    @Override
    public void eliminar(Lugar lugar) {
        lugarDao.delete(lugar);
    }

    @Override
    public Lugar encontrar(Lugar lugar) {
        return lugarDao.findById(lugar.getIdLugar()).orElse(null);
    }
}