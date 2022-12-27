package com.tesis.farmacia.dao;

import com.tesis.farmacia.entity.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenCompraDAO extends JpaRepository<OrdenCompra,Long> {
}
