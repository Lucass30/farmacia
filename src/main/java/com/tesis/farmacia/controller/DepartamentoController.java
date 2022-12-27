package com.tesis.farmacia.controller;

import com.tesis.farmacia.entity.Departamento;
import com.tesis.farmacia.entity.Provincia;
import com.tesis.farmacia.service.interfaces.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("departamento")
@RequestMapping(value = "/departamento")
public class DepartamentoController {


    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping(value = "/provincias", produces = { "application/json" })
    public @ResponseBody List<Provincia> cargarProvincias(@RequestParam(value = "id", required = true) Long term) {
        Departamento departamento = departamentoService.findOne(term);
        return departamento.getProvincias();
    }
}
