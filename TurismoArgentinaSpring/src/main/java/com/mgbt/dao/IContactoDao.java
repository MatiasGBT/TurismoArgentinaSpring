package com.mgbt.dao;

import com.mgbt.domain.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactoDao extends JpaRepository<Contacto, Integer> {
    Contacto findByEmail(String email);
}
