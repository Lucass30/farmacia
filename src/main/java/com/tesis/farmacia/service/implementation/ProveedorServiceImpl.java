package com.tesis.farmacia.service.implementation;

import com.tesis.farmacia.dao.IOrdenCompraDAO;
import com.tesis.farmacia.dao.IProductoDAO;
import com.tesis.farmacia.dao.IProveedorDAO;
import com.tesis.farmacia.entity.OrdenCompra;
import com.tesis.farmacia.entity.Producto;
import com.tesis.farmacia.entity.Proveedor;
import com.tesis.farmacia.service.interfaces.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private IProveedorDAO proveedorDAO;

    @Autowired
    private IOrdenCompraDAO ordenCompraDAO;

    @Autowired
    private IProductoDAO productoDAO;

    @Override
    public List<Proveedor> findAll() {
        return (List<Proveedor>) proveedorDAO.findAll();
    }

    @Override
    public Page<Proveedor> findAll(Pageable pageable) {
        return proveedorDAO.findAll(pageable);
    }

    @Override
    public void save(Proveedor proveedor) {
        proveedorDAO.save(proveedor);
    }

    @Override
    public Proveedor findOne(Long id) {
        return proveedorDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        proveedorDAO.deleteById(id);
    }

    @Override
    public List<Producto> findByNombre(String term) {
       return productoDAO.findByNombreLikeIgnoreCase("%" + term + "%");
    }

    @Override
    public void saveOrden(OrdenCompra ordenCompra) {
        ordenCompraDAO.save(ordenCompra);
    }

    @Override
    public Producto findMedicamentoById(Long id) {
        return productoDAO.findById(id).orElse(null);
    }

    @Override
    public OrdenCompra findOrdenCompraById(Long id) {
        return ordenCompraDAO.findById(id).orElse(null);
    }

    @Override
    public void deleteOrden(Long id) {
        ordenCompraDAO.deleteById(id);
    }
}
