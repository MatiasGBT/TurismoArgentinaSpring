package com.mgbt.service;

import com.mgbt.dao.IActividadDao;
import com.mgbt.domain.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadServiceImpl implements IService<Actividad> {

    @Autowired
    private IActividadDao actividadDao;

    @Override
    public List<Actividad> listar() {
        return actividadDao.findAll();
    }

    @Override
    public void guardar(Actividad actividad) {
        actividadDao.save(actividad);
    }

    @Override
    public void eliminar(Actividad actividad) {
        actividadDao.delete(actividad);
    }

    @Override
    public Actividad encontrar(Actividad actividad) {
        return actividadDao.findById(actividad.getIdActividad()).orElse(null);
    }

    public List<Actividad> listarAleatorios() {
        return actividadDao.listRandom();
    }
}
