package com.tesis.farmacia.service.implementation;

import com.tesis.farmacia.dao.IProvinciaDAO;
import com.tesis.farmacia.entity.Provincia;
import com.tesis.farmacia.service.interfaces.IProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaServiceImpl implements IProvinciaService {

    @Autowired
    private IProvinciaDAO provinciaDAO;

    @Override
    public List<Provincia> findAll() {
        return (List<Provincia>) provinciaDAO.findAll();
    }

    @Override
    public void save(Provincia provincia) {
        provinciaDAO.save(provincia);
    }

    @Override
    public Provincia findOne(Long id) {
        return provinciaDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        provinciaDAO.deleteById(id);
    }
}
