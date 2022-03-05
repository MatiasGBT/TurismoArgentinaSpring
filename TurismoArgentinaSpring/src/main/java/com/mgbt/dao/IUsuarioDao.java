package com.mgbt.dao;

import com.mgbt.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username); //Para seguridad/login
}
