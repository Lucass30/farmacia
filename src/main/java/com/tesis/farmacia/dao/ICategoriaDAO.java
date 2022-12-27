package com.tesis.farmacia.dao;

import com.tesis.farmacia.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaDAO extends JpaRepository<Categoria,Long> {
}
