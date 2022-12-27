package com.tesis.farmacia.service.interfaces;

import com.tesis.farmacia.entity.OrdenCompra;
import com.tesis.farmacia.entity.Producto;
import com.tesis.farmacia.entity.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProveedorService {

    public List<Proveedor> findAll();

    public Page<Proveedor> findAll(Pageable pageable);

    public void save (Proveedor proveedor);

    public Proveedor findOne(Long id);

    public void delete(Long id);

    public List<Producto> findByNombre(String term);

    public void saveOrden(OrdenCompra ordenCompra);

    public Producto findMedicamentoById(Long id);

    public OrdenCompra findOrdenCompraById(Long id);

    public void deleteOrden(Long id);
}
