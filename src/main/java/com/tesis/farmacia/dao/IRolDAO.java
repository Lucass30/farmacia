package com.tesis.farmacia.dao;

import com.tesis.farmacia.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolDAO extends JpaRepository<Rol,Long> {

    public Rol findByDescripcion(String descripcion);
}
