package com.tesis.farmacia.service.implementation;

import com.tesis.farmacia.dao.ICategoriaDAO;
import com.tesis.farmacia.entity.Categoria;
import com.tesis.farmacia.service.interfaces.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private ICategoriaDAO categoriaDAO;

    @Override
    public List<Categoria> findAll() {
        return (List<Categoria>) categoriaDAO.findAll();
    }

    @Override
    public Page<Categoria> findAll(Pageable pageable) {
        return categoriaDAO.findAll(pageable);
    }

    @Override
    public void save(Categoria tipo) {
        categoriaDAO.save(tipo);
    }

    @Override
    public Categoria findOne(Long id) {
        return categoriaDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        categoriaDAO.deleteById(id);
    }
}
