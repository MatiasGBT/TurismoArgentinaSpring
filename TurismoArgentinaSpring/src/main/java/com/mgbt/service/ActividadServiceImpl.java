package com.mgbt.service;

import com.mgbt.dao.IActividadDao;
import com.mgbt.domain.Actividad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActividadServiceImpl implements IService<Actividad> {

    @Autowired
    private IActividadDao actividadDao;

    @Override
    @Transactional(readOnly = true)
    public List<Actividad> listar() {
        return actividadDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Actividad actividad) {
        actividadDao.save(actividad);
    }

    @Override
    @Transactional
    public void eliminar(Actividad actividad) {
        actividadDao.delete(actividad);
    }

    @Override
    @Transactional(readOnly = true)
    public Actividad encontrar(Actividad actividad) {
        return actividadDao.findById(actividad.getIdActividad()).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Actividad> listarAleatorios() {
        return actividadDao.listRandom();
    }
}
