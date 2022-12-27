package com.tesis.farmacia.dao;

import com.tesis.farmacia.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProveedorDAO extends JpaRepository<Proveedor,Long> {
}
