package com.mgbt.dao;

import com.mgbt.domain.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILugarDao extends JpaRepository<Lugar, Integer> {
    
}
