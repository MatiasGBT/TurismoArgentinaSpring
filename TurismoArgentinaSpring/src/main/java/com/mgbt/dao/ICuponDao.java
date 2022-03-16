package com.mgbt.dao;

import com.mgbt.domain.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuponDao extends JpaRepository<Cupon, Integer> {
    Cupon findByNombre(String nombre);
}
