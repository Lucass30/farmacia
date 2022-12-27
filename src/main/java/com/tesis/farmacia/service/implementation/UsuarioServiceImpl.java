package com.tesis.farmacia.service.implementation;

import com.tesis.farmacia.dao.IUsuarioDAO;
import com.tesis.farmacia.entity.Rol;
import com.tesis.farmacia.entity.Usuario;
import com.tesis.farmacia.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioDAO.findAll(pageable);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    public Usuario findOne(Long id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        usuarioDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    @Override
    public Usuario getUsuario() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Usuario usuario = findByEmail(userDetail.getUsername());
        return usuario;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.findByEmail(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new User(usuario.getEmail(),usuario.getPassword(), mapearRoles(usuario.getRoles()));
    }
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByNombre(String term){
        return usuarioDAO.findByNombresLikeIgnoreCase("%" + term + "%");
    }

    private Collection<? extends GrantedAuthority> mapearRoles(List<Rol> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getDescripcion())).collect(Collectors.toList());
    }
}
