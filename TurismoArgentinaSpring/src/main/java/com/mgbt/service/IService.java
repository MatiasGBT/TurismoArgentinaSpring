package com.mgbt.service;

import java.util.List;

public interface IService<E> {
        
    public List<E> listar();

    public void guardar(E entity);

    public void eliminar(E entity);

    public E encontrar(E entity);
}
