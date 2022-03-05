package com.mgbt.service;

import com.mgbt.domain.Usuario;
import java.util.List;

public interface IUsuarioService {
    
    public List<Usuario> listarUsuarios();

    public void guardar(Usuario usuario);

    public void eliminar(Usuario usuario);

    public Usuario encontrarUsuario(Usuario usuario);
}
