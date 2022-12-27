package com.tesis.farmacia.service.implementation;

import com.tesis.farmacia.dao.IItemInventarioDAO;
import com.tesis.farmacia.entity.ItemInventario;
import com.tesis.farmacia.service.interfaces.IItemInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemInventarioServiceImpl implements IItemInventarioService {
    @Autowired
    private IItemInventarioDAO inventarioDAO;

    @Override
    public List<ItemInventario> findAll() {
        return (List<ItemInventario>) inventarioDAO.findAll();
    }

    @Override
    public void save(ItemInventario itemInventario) {
        inventarioDAO.save(itemInventario);

    }

    @Override
    public ItemInventario findOne(Long id) {
        return inventarioDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        inventarioDAO.deleteById(id);
    }
}
