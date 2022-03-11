package com.mgbt.service;

import com.mgbt.dao.IContactoDao;
import com.mgbt.domain.Contacto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactoServiceImpl implements IService<Contacto> {

    @Autowired
    IContactoDao contactoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Contacto> listar() {
        return contactoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Contacto contacto) {
        contactoDao.save(contacto);
    }

    @Override
    @Transactional
    public void eliminar(Contacto contacto) {
        contactoDao.delete(contacto);
    }

    @Override
    @Transactional(readOnly = true)
    public Contacto encontrar(Contacto contacto) {
        return contactoDao.findById(contacto.getIdContacto()).orElse(null);
    }

    @Transactional(readOnly = true)
    public Contacto encontrarContactoPorEmail(Contacto contacto) {
        return contactoDao.findByEmail(contacto.getEmail());
    }
}
