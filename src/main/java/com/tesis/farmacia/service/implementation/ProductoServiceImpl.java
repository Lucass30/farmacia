package com.tesis.farmacia.service.implementation;

import com.tesis.farmacia.dao.IProductoDAO;
import com.tesis.farmacia.entity.Producto;
import com.tesis.farmacia.service.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoDAO productoDAO;

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoDAO.findAll();
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) {
        return productoDAO.findAll(pageable);
    }

    @Override
    public void save(Producto medicamento) {
        productoDAO.save(medicamento);
    }

    @Override
    public Producto findOne(Long id) {
        return productoDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        productoDAO.deleteById(id);
    }
}
