package com.tesis.farmacia.service.interfaces;

import com.tesis.farmacia.entity.Inventario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInventarioService {
    public List<Inventario> findAll();

    public Page<Inventario> findAll(Pageable pageable);
    public void save (Inventario inventario);

    public Inventario findOne(Long id);

    public void delete(Long id);
}
