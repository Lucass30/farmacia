package com.tesis.farmacia.service.implementation;

import com.tesis.farmacia.dao.IDistritoDAO;
import com.tesis.farmacia.entity.Distrito;
import com.tesis.farmacia.service.interfaces.IDistritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistritoServiceImpl implements IDistritoService {

    @Autowired
    private IDistritoDAO distritoDAO;

    @Override
    public List<Distrito> findAll() {
        return (List<Distrito>) distritoDAO.findAll();
    }

    @Override
    public void save(Distrito distrito) {
        distritoDAO.save(distrito);
    }

    @Override
    public Distrito findOne(Long id) {
        return distritoDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        distritoDAO.deleteById(id);
    }
}
