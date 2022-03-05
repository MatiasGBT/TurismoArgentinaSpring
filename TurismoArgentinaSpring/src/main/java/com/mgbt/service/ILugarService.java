package com.mgbt.service;

import com.mgbt.domain.Lugar;
import java.util.List;

public interface ILugarService {
    
    public List<Lugar> listarLugares();

    public void guardar(Lugar lugar);

    public void eliminar(Lugar lugar);

    public Lugar encontrarLugar(Lugar lugar);
    
}
