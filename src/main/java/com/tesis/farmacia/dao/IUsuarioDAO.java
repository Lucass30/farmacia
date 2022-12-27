package com.tesis.farmacia.dao;


import com.tesis.farmacia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario,Long> {

    public Usuario findByEmail(String email);

    public List<Usuario> findByNombresLikeIgnoreCase(String term);
}
