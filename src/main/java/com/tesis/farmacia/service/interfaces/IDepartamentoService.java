package com.tesis.farmacia.service.interfaces;

import com.tesis.farmacia.entity.Departamento;

import java.util.List;

public interface IDepartamentoService {

    public List<Departamento> findAll();

    public void save(Departamento departamento);

    public Departamento findOne(Long id);

    public void delete(Long id);
}
