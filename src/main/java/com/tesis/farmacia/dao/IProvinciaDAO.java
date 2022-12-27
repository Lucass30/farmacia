package com.tesis.farmacia.dao;

import com.tesis.farmacia.entity.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinciaDAO extends JpaRepository<Provincia,Long> {

}
