package com.tesis.farmacia.service.implementation;

import com.tesis.farmacia.dao.IRolDAO;
import com.tesis.farmacia.entity.Rol;
import com.tesis.farmacia.service.interfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolDAO rolDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> findAll() {
        return (List<Rol>) rolDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Rol rol) {
        rolDAO.save(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public Rol findOne(Long id) {
        return rolDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rolDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Rol findByDescripcion(String descripcion) {
        return rolDAO.findByDescripcion(descripcion);
    }
}
