package com.tesis.farmacia.service.implementation;

import com.tesis.farmacia.dao.IInventarioDAO;
import com.tesis.farmacia.entity.Inventario;
import com.tesis.farmacia.service.interfaces.IInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServiceImpl implements IInventarioService {

    @Autowired
    private IInventarioDAO inventarioDAO;

    @Override
    public List<Inventario> findAll() {
        return (List<Inventario>) inventarioDAO.findAll();
    }

    @Override
    public Page<Inventario> findAll(Pageable pageable) {
        return inventarioDAO.findAll(pageable);
    }

    @Override
    public void save(Inventario inventario) {
        inventarioDAO.save(inventario);
    }

    @Override
    public Inventario findOne(Long id) {
        return inventarioDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        inventarioDAO.deleteById(id);
    }
}
