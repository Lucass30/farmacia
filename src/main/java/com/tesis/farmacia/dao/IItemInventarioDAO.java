package com.tesis.farmacia.dao;

import com.tesis.farmacia.entity.ItemInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemInventarioDAO extends JpaRepository<ItemInventario,Long> {
}
