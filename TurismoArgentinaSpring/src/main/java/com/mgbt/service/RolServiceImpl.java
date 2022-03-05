package com.mgbt.service;

import com.mgbt.dao.IRolDao;
import com.mgbt.domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements IRolService{
    
    @Autowired
    private IRolDao rolDao;
    
    @Override
    public Rol encontrarRol(Rol rol) {
        return rolDao.findByNombre(rol.getNombre());
    }
}
