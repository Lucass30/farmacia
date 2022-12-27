package com.tesis.farmacia.controller;

import com.tesis.farmacia.entity.Usuario;
import com.tesis.farmacia.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @Autowired
    private IUsuarioService usuarioService;

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public String index (Model model){
        Usuario usuario = usuarioService.getUsuario();
        model.addAttribute("titulo","Página de Inicio");
        model.addAttribute("usuario",usuario);
        return "layout/index";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String IniciarSesion (Model model){
        model.addAttribute("titulo", "Inicio de sesión");
        return "login/login";
    }
}
