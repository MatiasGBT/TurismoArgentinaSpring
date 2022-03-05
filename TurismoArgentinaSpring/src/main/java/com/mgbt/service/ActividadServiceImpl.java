package com.mgbt.service;

import com.mgbt.dao.IActividadDao;
import com.mgbt.domain.Actividad;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadServiceImpl implements IActividadService {

    @Autowired
    private IActividadDao actividadDao;

    @Override
    public List<Actividad> listarActividades() {
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
    public Actividad encontrarActividad(Actividad actividad) {
        return actividadDao.findById(actividad.getIdActividad()).orElse(null);
    }

    @Override
    public List<Actividad> listarAleatorios() {
        int r1, r2, r3;
        List<Actividad> actividades = new ArrayList<>();
        var actividad = new Actividad();
        do {
            r1 = (int) (Math.random() * listarActividades().size() + 1);
            r2 = (int) (Math.random() * listarActividades().size() + 1);
            r3 = (int) (Math.random() * listarActividades().size() + 1);
        } while (r1==r2 || r2==r3 || r1==r3);
        
        actividad.setIdActividad(r1);
        actividad = encontrarActividad(actividad);
        actividades.add(actividad);
        
        actividad.setIdActividad(r2);
        actividad = encontrarActividad(actividad);
        actividades.add(actividad);
        
        actividad.setIdActividad(r3);
        actividad = encontrarActividad(actividad);
        actividades.add(actividad);
        
        return actividades;
    }
}
