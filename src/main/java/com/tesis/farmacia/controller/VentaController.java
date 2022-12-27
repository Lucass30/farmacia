package com.tesis.farmacia.controller;

import com.tesis.farmacia.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("venta")
@RequestMapping("/venta")
public class VentaController {

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public String index (Model model){
        return "ventas/index";
    }
}
