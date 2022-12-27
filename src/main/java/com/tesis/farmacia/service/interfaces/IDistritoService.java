package com.tesis.farmacia.service.interfaces;

import com.tesis.farmacia.entity.Distrito;

import java.util.List;

public interface IDistritoService {

    public List<Distrito> findAll();

    public void save(Distrito distrito);

    public Distrito findOne(Long id);

    public void delete(Long id);
}
