package com.tesis.farmacia.service.interfaces;

import com.tesis.farmacia.entity.Provincia;

import java.util.List;

public interface IProvinciaService {

    public List<Provincia> findAll();

    public void save(Provincia provincia);

    public Provincia findOne(Long id);

    public void delete(Long id);
}
