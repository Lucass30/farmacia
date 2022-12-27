package com.tesis.farmacia.dao;

import com.tesis.farmacia.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentoDAO extends JpaRepository<Departamento,Long> {
}
