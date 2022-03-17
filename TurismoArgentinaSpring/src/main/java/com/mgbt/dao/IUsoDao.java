package com.mgbt.dao;

import com.mgbt.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsoDao extends JpaRepository<Uso, Integer> {
    Uso findByUsuarioAndCupon(Usuario usuario, Cupon cupon);
    
    void deleteByUsuario(Usuario usuario);
}
