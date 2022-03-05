package com.mgbt.dao;

import com.mgbt.domain.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActividadDao extends JpaRepository<Actividad, Integer> {
    
}
