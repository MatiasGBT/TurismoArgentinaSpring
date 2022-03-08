package com.mgbt.dao;

import com.mgbt.domain.Actividad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IActividadDao extends JpaRepository<Actividad, Integer> {
    
    @Query(value = "SELECT * FROM actividad ORDER BY RAND() LIMIT 0,3",
            nativeQuery = true)
    List<Actividad> listRandom();
}
