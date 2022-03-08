package com.mgbt.service;

import com.mgbt.dao.IRolDao;
import com.mgbt.domain.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements IService<Rol> {
    
    @Autowired
    private IRolDao rolDao;
    
    @Override
    public Rol encontrar(Rol rol) {
        return rolDao.findByNombre(rol.getNombre());
    }

    @Override
    public List<Rol> listar() {
        throw new UnsupportedOperationException("Esta implementación no soporta este método.");
    }

    @Override
    public void guardar(Rol entity) {
        throw new UnsupportedOperationException("Esta implementación no soporta este método.");
    }

    @Override
    public void eliminar(Rol entity) {
        throw new UnsupportedOperationException("Esta implementación no soporta este método.");
    }
}
