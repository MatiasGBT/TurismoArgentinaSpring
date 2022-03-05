package com.mgbt.service;

import com.mgbt.dao.IContactoDao;
import com.mgbt.domain.Contacto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoServiceImpl implements IContactoService {

    @Autowired
    IContactoDao contactoDao;
    
    @Override
    public List<Contacto> listarContactos() {
        return contactoDao.findAll();
    }

    @Override
    public void guardar(Contacto contacto) {
        contactoDao.save(contacto);
    }

    @Override
    public void eliminar(Contacto contacto) {
        contactoDao.delete(contacto);
    }

    @Override
    public Contacto encontrarContacto(Contacto contacto) {
        return contactoDao.findById(contacto.getIdContacto()).orElse(null);
    }

    @Override
    public Contacto encontrarContactoPorEmail(Contacto contacto) {
        return contactoDao.findByEmail(contacto.getEmail());
    }
    
}
