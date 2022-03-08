package com.mgbt.service;

import com.mgbt.dao.IUsuarioDao;
import com.mgbt.domain.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioServiceImpl implements UserDetailsService, IService<Usuario> {

    @Autowired
    private IUsuarioDao usuarioDao;
    
    //SEGURIDAD SPRING
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        var roles = new ArrayList<GrantedAuthority>(); //Este es el tipo que necesita Spring para poder manejar los roles
        Rol rol = usuario.getRol();
        roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        
        //Este objeto de tipo User es de Spring
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

    //INTERFACE IUSUARIOSERVICE
    @Override
    public List<Usuario> listar() {
        return usuarioDao.findAll();
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public void eliminar(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public Usuario encontrar(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }
}
