package com.tesis.farmacia.dao;

import com.tesis.farmacia.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoDAO extends JpaRepository<Producto,Long> {

    public List<Producto> findByNombre(String term);

    public List<Producto> findByNombreLikeIgnoreCase(String term);
}
