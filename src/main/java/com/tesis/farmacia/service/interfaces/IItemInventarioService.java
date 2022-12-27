package com.tesis.farmacia.service.interfaces;

import com.tesis.farmacia.entity.ItemInventario;

import java.util.List;

public interface IItemInventarioService {
    public List<ItemInventario> findAll();

    public void save (ItemInventario itemInventario);

    public ItemInventario findOne(Long id);

    public void delete(Long id);
}
