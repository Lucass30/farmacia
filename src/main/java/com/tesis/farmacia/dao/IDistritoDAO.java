package com.tesis.farmacia.dao;

import com.tesis.farmacia.entity.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistritoDAO extends JpaRepository<Distrito,Long> {
}
