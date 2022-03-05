package com.mgbt.service;

import com.mgbt.domain.Contacto;
import java.util.List;

public interface IContactoService {
    
    public List<Contacto> listarContactos();

    public void guardar(Contacto contacto);

    public void eliminar(Contacto contacto);

    public Contacto encontrarContacto(Contacto contacto);
    
    public Contacto encontrarContactoPorEmail(Contacto contacto);
}
