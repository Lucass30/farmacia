package com.tesis.farmacia.dao;

import com.tesis.farmacia.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventarioDAO extends JpaRepository<Inventario,Long> {
}
