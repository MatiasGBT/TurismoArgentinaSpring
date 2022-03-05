package com.mgbt.service;

import com.mgbt.domain.Actividad;
import java.util.List;

public interface IActividadService {
        
    public List<Actividad> listarActividades();

    public void guardar(Actividad actividad);

    public void eliminar(Actividad actividad);

    public Actividad encontrarActividad(Actividad actividad);
    
    public List<Actividad> listarAleatorios();
}
