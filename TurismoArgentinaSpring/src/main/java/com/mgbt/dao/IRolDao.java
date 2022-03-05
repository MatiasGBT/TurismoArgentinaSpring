package com.mgbt.dao;

import com.mgbt.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolDao extends JpaRepository<Rol, Integer> {
    Rol findByNombre(String nombre);
}
